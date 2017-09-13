var personListContainer = document.getElementById("table_list");

function printPersonTable() {

    personListContainer.innerHTML += "<div class='table' id='t_container'></div";
    var tableContainer = document.getElementById("t_container");

    var url = "http://localhost:8080/CA1/api/person/complete/";
    var conf = {method: "get"};
    var promise = fetch(url, conf);

    function printSub(array, location) {
        var stringStart = "<div class='table-cell'>";
        var stringEnd = "</div>";

        for (var y = 0; y < array.length; y++) {
            if (location === "number") {
                stringStart += array[y].number + "<br>";
            } else if (location === "description") {
                stringStart += array[y].description + "<br>";
            }
            
        }

        return stringStart + stringEnd;
    }

    promise.then(function (response) {
        return response.json();
    }).then(function (data) {

        for (var i = 0; i < data.length; i++) {
            tableContainer.innerHTML += "<div class='table-row'><div class='table-cell'>" + data[i].id + "</div>" +
                    "<div class='table-cell'>" + data[i].email + "</div>" +
                    "<div class='table-cell'>" + data[i].firstname + "</div>" +
                    "<div class='table-cell'>" + data[i].lastname + "</div>" +
                    "<div class='table-cell'>" + data[i].address.street + "</div>" +
                    
                    printSub(data[i].phones, "number") +
                    printSub(data[i].hobbies, "description");

        }

    });

}

printPersonTable();

