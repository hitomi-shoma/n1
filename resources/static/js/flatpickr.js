//let maxDate = new Date();
//maxDate = maxDate.setMonth(maxDate.getMonth() + 3);
//
//flatpickr('#reservationDateTime', {
//	mode: "range",
//	locale: 'ja',
//	dateFormat:'Y.m.d.H.m',
//	enableTime : true,
//	minTime : '09:00',
//	maxTime : '18:00'
//});

flatpickr('#reservationDateTime', {
    mode: "single",
    locale: 'ja',
    dateFormat: 'Y-m-d',
    enableTime: true,
    minDate: new Date(),
    maxDate: new Date().fp_incr(90),
    time_24hr: true,
    minuteIncrement: 30,
    enableSeconds: false,
    noCalendar: false,
    onChange: function(selectedDates, dateStr, instance) {
        // 時間の選択肢を固定する
        instance.set('allowInput', false);
        instance.set('maxTime', '20:30');
        instance.set('minTime', '07:00');
        instance.set('time_24hr', true);
        instance.set('enableSeconds', false);
        instance.set('minuteIncrement', 30);
        instance.set('dateFormat', 'Y-m-d H:i');
        instance.set('disableMobile', true);
        instance.set('enableTime', true);
        instance.set('noCalendar', false);
        instance.set('time', ['07:00', '07:30', '08:00', '08:30', '09:00', '09:30', '10:00', '10:30', '11:00', '11:30', '12:00', '12:30', '13:00', '13:30', '14:00', '14:30', '15:00', '15:30', '16:00', '16:30', '17:00', '17:30', '18:00', '18:30', '19:00', '19:30', '20:00', '20:30']);
    }
});
