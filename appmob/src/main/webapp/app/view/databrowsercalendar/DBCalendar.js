Ext.define('Appmob.view.databrowsercalendar.DBCalendar', {
	extend : 'Appmob.view.databrowsercalendar.DBCalendarView',
	requires : [ 'Appmob.view.databrowsercalendar.DBCalendarController',
	             'Appmob.view.databrowsercalendar.DBCalendarView','Ext.layout.container.Card',
	             'Ext.calendar.view.Day', 'Ext.calendar.view.Week',
	             'Ext.calendar.view.Month',
	             'Ext.calendar.form.EventDetails',
	             'Ext.calendar.data.EventMappings'],
	controller : 'databrowsercalendar',
	items : [],
	/*listeners : {
		afterrender : 'loadData',
		scope : "controller"
	}*/
});
