var personListContainer = document.getElementById("table_list");
var personById = document.getElementById("person_by_id");
var fieldPersonById = document.getElementById("field_person_id");

function printPersonTable(toUrl, method_type) {

    var url = toUrl;
    var conf = {method: method_type};
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
        personListContainer.innerHTML = "";
        personListContainer.innerHTML += "<div class='table' id='t_container'></div";
        var tableContainer = document.getElementById("t_container");
        tableContainer.innerHTML = "";
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

// First page load
printPersonTable("http://localhost:8080/CA1/api/person/complete", "GET");

// Button events
personById.addEventListener("click", function () {
    printPersonTable("http://localhost:8080/CA1/api/person/complete/" + fieldPersonById.value, "GET");
}, false);


