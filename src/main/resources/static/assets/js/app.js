$(document).ready(function() {
    var placeholderText = {"Sell":"Selling price","Rent":"Renting price per day"};
    $("#PurchaseType").on("change",function() {
        var selection = $("#PurchaseType");
        var inputBox = $("#Price");

        var selectedVal = $(':selected', selection).text();
        if (placeholderText[selectedVal] !== undefined) {
            inputBox.attr('placeholder', placeholderText[selectedVal]);
        }
    });
});