var personListContainer = document.getElementById("table_list");

function printPersonTable() {

    personListContainer.innerHTML += "<div class='table' id='t_container'></div";
    var tableContainer = document.getElementById("t_container");

    var url = "http://localhost:8080/CA1/api/person/complete/";
    var conf = {method: "get"};
    var promise = fetch(url, conf);

    promise.then(function (response) {
        return response.json();
    }).then(function (data) {
        for (var i = 0; i < data.length; i++) {
            tableContainer.innerHTML += "<div class='table-row'><div class='table-cell'>" + data[i].fname + "</div>" +
                    "<div class='table-cell'>" + data[i].lname + "</div>" +
                    "<div class='table-cell'>" + data[i].id + "</div>" +
                    "<div class='table-cell'>" + data[i].age + "</div>";
        }

    });

}

printPersonTable();

