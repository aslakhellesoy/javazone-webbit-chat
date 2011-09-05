window.onload = function() {
    function send(message) {
        console.log(message);
    }

    var entry = document.getElementById('entry');
    entry.onkeypress = function(e) {
        if (entry.value && e.keyCode == 13) {
            send(entry.value);
            entry.value = '';
        }
    };
}