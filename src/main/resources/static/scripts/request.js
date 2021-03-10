
function getRequest(path, method) {
    let ajax = new XMLHttpRequest();
    ajax.onerror = (request) => {
        console.error(request.responseText);
    }

    ajax.open(method, path, true);
    return ajax;
}