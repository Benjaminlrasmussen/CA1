var ListContainer = document.getElementById("table_list");
var personById = document.getElementById("person_by_id");
var fieldPersonById = document.getElementById("field_person_id");
var hobbySelect = document.getElementById("hobbySelect");
var fieldEmpCount = document.getElementById("field_emp_count");
var empByCount = document.getElementById("emp_by_count");

// Reusable all glory fetch method
function printPersonTable(toUrl, method_type, data_type) {

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

        if (data_type === "person" && data.length > 0) {

            ListContainer.innerHTML = "";
            ListContainer.innerHTML += "<div class='table' id='t_container'></div";
            var tableContainer = document.getElementById("t_container");
            //tableContainer.innerHTML = "";
            for (var i = 0; i < data.length; i++) {

                tableContainer.innerHTML += "<div class='table-row'><div class='table-cell'>" + data[i].id + "</div>" +
                        "<div class='table-cell'>" + data[i].email + "</div>" +
                        "<div class='table-cell'>" + data[i].firstname + "</div>" +
                        "<div class='table-cell'>" + data[i].lastname + "</div>" +
                        "<div class='table-cell'>" + data[i].address.street + "</div>" +
                        printSub(data[i].phones, "number") +
                        printSub(data[i].hobbies, "description");

            }

        } else if (data_type === "company" && data.length > 0) {

            ListContainer.innerHTML = "";
            ListContainer.innerHTML += "<div class='table' id='t_container'></div";
            var tableContainer = document.getElementById("t_container");
            //tableContainer.innerHTML = "";

            for (var i = 0; i < data.length; i++) {
                tableContainer.innerHTML += "<div class='table-row'><div class='table-cell'>" + data[i].id + "</div>" +
                        "<div class='table-cell'>" + data[i].name + "</div>" +
                        "<div class='table-cell'>" + data[i].description + "</div>" +
                        "<div class='table-cell'>" + data[i].cvr + "</div>" +
                        "<div class='table-cell'>" + data[i].numEmployees + "</div>" +
                        printSub(data[i].phones, "number") +
                        printSub(data[i].address, "street");


            }

        } else {
            ListContainer.innerHTML = "No results";
        }





    });


}

// Sort and collect from json object
function getList(jsonUrl, type) {

    var url = jsonUrl;
    var conf = {method: "GET"};
    var promise = fetch(url, conf);

    var stringArray = [];

    function findSub(array, type) {
        for (var y = 0; y < array.length; y++) {
            if (stringArray[0] == null) {
                console.log("ping");
                stringArray.push(array[y].name);
            } else {
                var isHere = false;
                for (var i = 0; i < stringArray.length; i++) {
                    if (stringArray[i] === array[y].name) {
                        isHere = true;
                    }
                }
                if (isHere === false) {
                    stringArray.push(array[y].name);
                }
            }
        }
        return stringArray;
    }

    promise.then(function (response) {
        return response.json();
    }).then(function (data) {

        for (var i = 0; i < data.length; i++) {
            console.log(findSub(data[i].hobbies));
        }
        for (var key in stringArray) {
            hobbySelect.innerHTML += "<option>" + stringArray[key] + "</option>";
        }

    });

}

getList("/CA1/api/person/complete", "description", "person");

// First page load
printPersonTable("/CA1/api/person/complete", "GET", "person");

// Button events
personById.addEventListener("click", function () {
    printPersonTable("/CA1/api/person/complete/" + fieldPersonById.value, "GET", "person");
}, false);

empByCount.addEventListener("click", function () {
    printPersonTable("/CA1/api/company/employees/" + fieldEmpCount.value, "GET", "company");
}, false);

//On change event
hobbySelect.addEventListener("change", function () {
    if (hobbySelect.value !== "Sort on hobbies...") {
        printPersonTable("/CA1/api/person/hobby/" + hobbySelect.value, "GET", "person");
    } else {
        printPersonTable("/CA1/api/person/complete", "GET", "person");
    }
});

