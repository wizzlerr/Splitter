/**
 * Created by Adam on 2017-03-12.
 */

$( document ).ready(function() {
    $('#messages').delay(10000).fadeOut('slow');
    $('.dropdown-toggle').dropdown();

    $('#datetimepicker4').datetimepicker({
        pickTime: false
    });
});
