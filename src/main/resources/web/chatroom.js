window.onload = function() {
    var ws = new WebSocket('ws://' + document.location.host + "/chat");
    ws.onopen    = function(e) { console.log('OPEN'); };
    ws.onclose   = function(e) { console.log('CLOSE'); };
    ws.onerror   = function(e) { console.log('ERROR'); };
    ws.onmessage = function(e) { console.log('MESSAGE'); };

    function send(message) {
        ws.send(message);
    }

    var entry = document.getElementById('entry');
    entry.onkeypress = function(e) {
        if (entry.value && e.keyCode == 13) {
            send(entry.value);
            entry.value = '';
        }
    };
}