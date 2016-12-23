Ext.define('Appmob.appmob.shared.com.model.appbasicsetup.usermanagement.PassRecoveryModel', {
     "extend": "Ext.data.Model",
     "fields": [{
          "name": "primaryKey",
          "type": "auto",
          "defaultValue": ""
     }, {
          "name": "passRecoveryId",
          "type": "string",
          "defaultValue": ""
     }, {
          "name": "questionid",
          "reference": "Question",
          "defaultValue": ""
     }, {
          "name": "questionQuestionName",
          "type": "auto",
          "defaultValue": ""
     }, {
          "name": "answer",
          "type": "string",
          "defaultValue": ""
     }, {
          "name": "versionId",
          "type": "int",
          "defaultValue": ""
     }, {
          "name": "UserDetail",
          "reference": "UserDetailModel"
     }, {
          "name": "entityAudit",
          "reference": "EntityAudit"
     }, {
          "name": "primaryDisplay",
          "type": "string",
          "defaultValue": ""
     }]
});