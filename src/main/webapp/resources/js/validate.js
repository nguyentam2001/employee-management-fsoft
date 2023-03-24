jQuery.validator.addMethod("password", function (value, element) {
    return this.optional(element) || /^((?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).{6,})$/.test(value);
}, "Password must have at least 6 characters include uppercase, lowercase, number");

$("form[role='form']").validate({
    rules:{
        "account.email": {
            required: true,
            maxlength:50
        },
        "account.password": {
            required: true,
            password: true
        },
        "re-password": {
            equalTo: "#password",
            rangelength: [8,50]
        },
        "firstName": {
            required: true,
            maxlength:50
        },
        "lastName": {
            required: true,
            maxlength:50
        },
        "phone": {
            required: true,
            rangelength: [6,13]
        },

         "account.account": {
            required: true,
            maxlength: 50
         },
        "departmentName":{
            required: true,
            maxlength: 50
        }
    },
    messages: {
        "account.password": {
            required: "Password is required",
        },
        "re-password": {
            equalTo: "Password must be the same",
            maxlength: "Password must enter between 8 and 10 characters"
        },
        "firstname": {
            required: "Firstname is required",
            maxlength: "Firstname must enter between 3 and 30 characters"

        },
        "lastname": {
            required: "Lastname is required",
            maxlength: "Lastname must enter between 3 and 30 characters"
        },
        "phone": {
            required: "Phone is required",
            maxlength: "Phone must enter less than 50 characters"
        },
         "account.account": {
             required: "Account is required",
             maxlength: "Account must enter less than 50 characters"
         },

          "departmentName": {
                      required: "Department is required",
                      maxlength: "Department must enter less than 50 characters"
          },
    }

})