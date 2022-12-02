var xhrHttp = null;
if (window.XMLHttpRequest) {
    xhrHttp = new XMLHttpRequest();
} else {
    xhrHttp = new ActiveXObject("Microsoft.XMLHTTP");
}

function getRequest(url,callback) {
    xhrHttp.open("GET", url, true);
    xhrHttp.send();
    xhrHttp.onreadystatechange = function () {
        if (xhrHttp.readyState === 4 && xhrHttp.status === 200) {
            callback(JSON.parse(xhrHttp.response));
        }
    };
}
