/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.document.library.asset.auto.tagger.tensorflow.internal.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.util.Arrays;
import java.util.List;

import org.tensorflow.Graph;
import org.tensorflow.Output;
import org.tensorflow.Session;
import org.tensorflow.Tensor;

/**
 * See https://github.com/tensorflow/tensorflow/blob/master/tensorflow/java/src/main/java/org/tensorflow/examples/LabelImage.java
 *
 * @author Shuyang Zhou
 * @author Alejandro Tardín
 */
public class InceptionImageLabelerUtil {

	public static float[] getLabelProbabilities(byte[] imageBytes) {
		try (Tensor<Float> imageTensor = _normalizeImage(imageBytes);
			Tensor<Float> resultTensor = _getOutputTensor(
				_imageLabelerGraph, imageTensor)) {

			long[] shape = resultTensor.shape();

			if ((resultTensor.numDimensions() != 2) || (shape[0] != 1)) {
				throw new RuntimeException(
					String.format(
						"Expected model to produce a [1 N] shaped tensor " +
							"where N is the number of labels, instead it " +
								"produced one with shape %s",
						Arrays.toString(shape)));
			}

			int numberOfLabels = (int)shape[1];

			return resultTensor.copyTo(new float[1][numberOfLabels])[0];
		}
	}

	private static Tensor<Float> _getOutputTensor(
		Graph graph, Tensor<Float> inputTensor) {

		try (Session session = new Session(graph)) {
			Session.Runner runner = session.runner();

			runner = runner.feed("input", inputTensor);
			runner = runner.fetch("output");

			List<Tensor<?>> tensors = runner.run();

			Tensor<?> resultTensor = tensors.get(0);

			return resultTensor.expect(Float.class);
		}
	}

	private static Tensor<Float> _normalizeImage(byte[] imageBytes) {
		try (Tensor tensor = Tensor.create(imageBytes, String.class)) {
			return _getOutputTensor(_imageNormalizerGraph, tensor);
		}
	}

	private static final Graph _imageLabelerGraph;
	private static final Graph _imageNormalizerGraph;

	static {
		try (InputStream inputStream =
				InceptionImageLabelerUtil.class.getResourceAsStream(
					"/META-INF/tensorflow/tensorflow_inception_graph.pb")) {

			byte[] buffer = new byte[1024];

			try (ByteArrayOutputStream byteArrayOutputStream =
					new ByteArrayOutputStream()) {

				int size = -1;

				while ((size = inputStream.read(buffer)) != -1) {
					byteArrayOutputStream.write(buffer, 0, size);
				}

				_imageLabelerGraph = new Graph();

				_imageLabelerGraph.importGraphDef(
					byteArrayOutputStream.toByteArray());
			}

			_imageNormalizerGraph = new Graph();

			GraphBuilder graphBuilder = new GraphBuilder(_imageNormalizerGraph);

			Output<String> output = graphBuilder.placeholder(
				"input", String.class);

			graphBuilder.rename(
				graphBuilder.div(
					graphBuilder.sub(
						graphBuilder.resizeBilinear(
							graphBuilder.expandDims(
								graphBuilder.cast(
									graphBuilder.decodeJpeg(output, 3),
									Float.class),
								graphBuilder.constant("make_batch", 0)),
							graphBuilder.constant(
								"size", new int[] {224, 224})),
						graphBuilder.constant("mean", 117F)),
					graphBuilder.constant("scale", 1F)),
				"output"
			);
		}
		catch (IOException ioe) {
			throw new ExceptionInInitializerError(ioe);
		}
	}

}