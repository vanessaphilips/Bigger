function getFormData($form){
    let unindexed_array = $form.serializeArray();
    let indexed_array = {};

    $.map(unindexed_array, function(n, i){
        indexed_array[n['id']] = n['value'];
    });

    return indexed_array;
}

let $form = $("#form_data");
let data = getFormData($form);
