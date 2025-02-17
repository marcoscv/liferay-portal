AUI.add(
	'liferay-portlet-dynamic-data-mapping-custom-fields',
	function(A) {
		var FormBuilderTextField = A.FormBuilderTextField;
		var FormBuilderTypes = A.FormBuilder.types;

		var booleanParse = A.DataType.Boolean.parse;
		var camelize = Liferay.Util.camelize;
		var trim = A.Lang.trim;

		var STR_BLANK = '';

		var TPL_BUTTON = '<div class="field-labels-inline">' +
							'<input type="button" value="' + A.Escape.html(Liferay.Language.get('select')) + '" />' +
						'<div>';

		var TPL_GEOLOCATION = '<div class="field-labels-inline">' +
									'<input type="button" value="' + A.Escape.html(Liferay.Language.get('geolocate')) + '" />' +
								'<div>';

		var TPL_LINK_TO_PAGE = '<div class="lfr-ddm-link-to-page">' +
								'<a href="javascript:;">' + Liferay.Language.get('link') + '</a>' +
							'</div>';

		var TPL_PARAGRAPH = '<p></p>';

		var TPL_SEPARATOR = '<div class="separator"></div>';

		var TPL_TEXT_HTML = '<textarea class="form-builder-field-node lfr-ddm-text-html"></textarea>';

		var TPL_WCM_IMAGE = '<div class="lfr-wcm-image"></div>';

		var applyStyles = function(node, styleContent) {
			var styles = styleContent.replace(/\n/g, STR_BLANK).split(';');

			node.setStyle(STR_BLANK);

			A.Array.each(
				styles,
				function(item, index) {
					var rule = item.split(':');

					if (rule.length == 2) {
						var key = camelize(rule[0]);
						var value = trim(rule[1]);

						node.setStyle(key, value);
					}
				}
			);
		};

		var LiferayFormBuilderField = function() {
		};

		LiferayFormBuilderField.ATTRS = {
			autoGeneratedName: {
				setter: booleanParse,
				value: true
			},

			indexType: {
				value: 'keyword'
			},

			localizable: {
				setter: booleanParse,
				value: true
			},

			name: {
				setter: Liferay.FormBuilder.normalizeKey,
				valueFn: function() {
					var instance = this;

					return A.FormBuilderField.buildFieldName(instance.get('label'));
				}
			},

			repeatable: {
				setter: booleanParse,
				value: false
			}
		};

		A.Base.mix(A.FormBuilderField, [LiferayFormBuilderField]);

		var FormBuilderProto = A.FormBuilderField.prototype;

		var originalGetPropertyModel = FormBuilderProto.getPropertyModel;

		FormBuilderProto.getPropertyModel = function() {
			var instance = this;

			var model = originalGetPropertyModel.call(instance);

			var type = instance.get('type');

			var indexTypeOptions = {
				'': Liferay.Language.get('no'),
				'keyword': Liferay.Language.get('yes')
			};

			if ((type == 'ddm-text-html') || (type == 'text') || (type == 'textarea')) {
				indexTypeOptions = {
					'': Liferay.Language.get('not-indexable'),
					'keyword': Liferay.Language.get('indexable-keyword'),
					'text': Liferay.Language.get('indexable-text')
				};
			}

			var booleanOptions = {
				'false': Liferay.Language.get('no'),
				'true': Liferay.Language.get('yes')
			};

			return model.concat(
				[
					{
						attributeName: 'indexType',
						editor: new A.RadioCellEditor(
							{
								options: indexTypeOptions
							}
						),
						formatter: function(val) {
							return indexTypeOptions[val.data.value];
						},
						name: Liferay.Language.get('indexable')
					},
					{
						attributeName: 'localizable',
						editor: new A.RadioCellEditor(
							{
								options: booleanOptions
							}
						),
						formatter: function(val) {
							return booleanOptions[val.data.value];
						},
						name: Liferay.Language.get('localizable')
					},
					{
						attributeName: 'repeatable',
						editor: new A.RadioCellEditor(
							{
								options: booleanOptions
							}
						),
						formatter: function(val) {
							return booleanOptions[val.data.value];
						},
						name: Liferay.Language.get('repeatable')
					}
				]
			);
		};

		var DDMDateField = A.Component.create(
			{
				ATTRS: {
					dataType: {
						value: 'date'
					},

					fieldNamespace: {
						value: 'ddm'
					}
				},

				EXTENDS: A.FormBuilderTextField,

				NAME: 'ddm-date',

				prototype: {
					renderUI: function() {
						var instance = this;

						DDMDateField.superclass.renderUI.apply(instance, arguments);

						instance.datePicker = new A.DatePicker(
							{
								calendar: {
									locale: Liferay.ThemeDisplay.getLanguageId(),
									strings: {
										next: Liferay.Language.get('next'),
										none: Liferay.Language.get('none'),
										previous: Liferay.Language.get('previous'),
										today: Liferay.Language.get('today')
									}
								},
								trigger: instance.get('templateNode')
							}
						).render();
					},

					getPropertyModel: function() {
						var instance = this;

						var model = DDMDateField.superclass.getPropertyModel.apply(instance, arguments);

						A.Array.each(
							model,
							function(item, index, collection) {
								var attributeName = item.attributeName;

								if (attributeName === 'predefinedValue') {
									collection[index] = {
										attributeName: attributeName,
										editor: new A.DateCellEditor(
											{
												dateFormat: '%m/%d/%Y'
											}
										),
										name: Liferay.Language.get('predefined-value')
									};
								}
							}
						);

						return model;
					}
				}
			}
		);

		var DDMDecimalField = A.Component.create(
			{
				ATTRS: {
					dataType: {
						value: 'double'
					},

					fieldNamespace: {
						value: 'ddm'
					}
				},

				EXTENDS: A.FormBuilderTextField,

				NAME: 'ddm-decimal'
			}
		);

		var DDMDocumentLibraryField = A.Component.create(
			{
				ATTRS: {
					dataType: {
						value: 'document-library'
					},

					fieldNamespace: {
						value: 'ddm'
					}
				},

				EXTENDS: A.FormBuilderField,

				NAME: 'ddm-documentlibrary',

				prototype: {
					getHTML: function() {
						return TPL_BUTTON;
					},

					getPropertyModel: function() {
						var instance = this;

						var model = DDMDocumentLibraryField.superclass.getPropertyModel.apply(instance, arguments);

						A.Array.each(
							model,
							function(item, index) {
								var attributeName = item.attributeName;
								var DLFileEntryCellEditor = Liferay.SpreadSheet.TYPE_EDITOR['ddm-documentlibrary'];

								if (attributeName === 'predefinedValue') {
									item.editor = new DLFileEntryCellEditor();

									item.formatter = function(obj) {
										var data = obj.data;

										var label = STR_BLANK;

										var value = data.value;

										if (value !== STR_BLANK) {
											label = '(' + Liferay.Language.get('file') + ')';
										}

										return label;
									};
								}
								else if (attributeName === 'type') {
									item.formatter = instance._defaultFormatter;
								}
							}
						);

						return model;
					},

					_defaultFormatter: function() {
						var instance = this;

						return 'documents-and-media';
					},

					_uiSetValue: function() {
						return Liferay.Language.get('select');
					}

				}

			}
		);

		var DDMGeolocationField = A.Component.create(
			{
				ATTRS: {
					dataType: {
						value: 'geolocation'
					},

					fieldNamespace: {
						value: 'ddm'
					}
				},

				EXTENDS: A.FormBuilderTextField,

				NAME: 'ddm-geolocation',

				prototype: {
					getHTML: function() {
						return TPL_GEOLOCATION;
					}
				}
			}
		);

		var DDMImageField = A.Component.create(
			{
				ATTRS: {
					dataType: {
						value: 'image'
					},

					fieldNamespace: {
						value: 'ddm'
					}
				},

				EXTENDS: A.FormBuilderField,

				NAME: 'ddm-image',

				prototype: {
					getHTML: function() {
						return TPL_WCM_IMAGE;
					}
				}
			}
		);

		var DDMIntegerField = A.Component.create(
			{
				ATTRS: {
					dataType: {
						value: 'integer'
					},

					fieldNamespace: {
						value: 'ddm'
					}
				},

				EXTENDS: A.FormBuilderTextField,

				NAME: 'ddm-integer'
			}
		);

		var DDMNumberField = A.Component.create(
			{
				ATTRS: {
					dataType: {
						value: 'number'
					},

					fieldNamespace: {
						value: 'ddm'
					}
				},

				EXTENDS: A.FormBuilderTextField,

				NAME: 'ddm-number'
			}
		);

		var DDMParagraphField = A.Component.create(
			{
				ATTRS: {
					dataType: {
						value: undefined
					},

					fieldNamespace: {
						value: 'ddm'
					},

					showLabel: {
						readOnly: true,
						value: true
					},

					style: {
						value: STR_BLANK
					}
				},

				EXTENDS: A.FormBuilderField,

				NAME: 'ddm-paragraph',

				UI_ATTRS: ['label', 'style'],

				prototype: {
					getHTML: function() {
						return TPL_PARAGRAPH;
					},

					getPropertyModel: function() {
						var instance = this;

						return [
							{
								attributeName: 'type',
								editor: false,
								name: Liferay.Language.get('type')
							},
							{
								attributeName: 'label',
								editor: new A.TextAreaCellEditor(),
								name: Liferay.Language.get('text')
							},
							{
								attributeName: 'style',
								editor: new A.TextAreaCellEditor(),
								name: Liferay.Language.get('style')
							}
						];
					},

					_uiSetLabel: function(val) {
						var instance = this;

						instance.get('templateNode').setContent(val);
					},

					_uiSetStyle: function(val) {
						var instance = this;

						var templateNode = instance.get('templateNode');

						applyStyles(templateNode, val);
					}
				}
			}
		);

		var DDMSeparatorField = A.Component.create(
			{
				ATTRS: {
					dataType: {
						value: undefined
					},

					fieldNamespace: {
						value: 'ddm'
					},

					showLabel: {
						value: false
					},

					style: {
						value: STR_BLANK
					}
				},

				EXTENDS: A.FormBuilderField,

				NAME: 'ddm-separator',

				UI_ATTRS: ['style'],

				prototype: {
					getHTML: function() {
						return TPL_SEPARATOR;
					},

					getPropertyModel: function() {
						var instance = this;

						var model = DDMSeparatorField.superclass.getPropertyModel.apply(instance, arguments);

						model.push(
							{
								attributeName: 'style',
								editor: new A.TextAreaCellEditor(),
								name: Liferay.Language.get('style')
							}
						);

						return model;
					},

					_uiSetStyle: function(val) {
						var instance = this;

						var templateNode = instance.get('templateNode');

						applyStyles(templateNode, val);
					}
				}
			}
		);

		var DDMHTMLTextField = A.Component.create(
			{
				ATTRS: {
					dataType: {
						value: 'html'
					},

					fieldNamespace: {
						value: 'ddm'
					}
				},

				EXTENDS: FormBuilderTextField,

				NAME: 'ddm-text-html',

				prototype: {
					getHTML: function() {
						return TPL_TEXT_HTML;
					}
				}
			}
		);

		var DDMLinkToPageField = A.Component.create(
			{
				ATTRS: {
					dataType: {
						value: 'link-to-page'
					},

					fieldNamespace: {
						value: 'ddm'
					}
				},

				EXTENDS: FormBuilderTextField,

				NAME: 'ddm-link-to-page',

				prototype: {
					getHTML: function() {
						return TPL_LINK_TO_PAGE;
					}
				}
			}
		);

		var plugins = [
			DDMDateField,
			DDMDecimalField,
			DDMDocumentLibraryField,
			DDMGeolocationField,
			DDMImageField,
			DDMIntegerField,
			DDMLinkToPageField,
			DDMNumberField,
			DDMParagraphField,
			DDMSeparatorField,
			DDMHTMLTextField
		];

		A.Array.each(
			plugins,
			function(item, index) {
				FormBuilderTypes[item.NAME] = item;
			}
		);
	},
	'',
	{
		requires: ['liferay-portlet-dynamic-data-mapping']
	}
);