document.addEventListener("DOMContentLoaded", function() {
    loadRooms(0, 10);
  });

function loadRooms(page, count) {
    let ajax = getRequest(`/api/room/find?page=${page}&count=${count}`, "GET")
    ajax.onreadystatechange = () => {
        console.log(ajax.readyState)
        if(ajax.readyState == 4 && ajax.status == 200){
            console.log(ajax.responseText)
            mountDOM(JSON.parse(ajax.response))
        }
    }
    ajax.send();
}

function mountDOM(rooms) {
    if(!rooms)
        rooms = []

    let table = document.getElementById("rooms-table")
    console.log(rooms.length)
    for(let i = 0; i < rooms.length; i++){
        console.log(i)
        let line = document.createElement("tr")
        line.innerHTML = `${rooms[i]['name']}`
        let enter = document.createElement("button")
        enter.innerText ="Entrar"
        enter.onclick = () => {
            connect(rooms[i]['id'])
        }
        line.append(enter)
        table.append(line)
    }
}