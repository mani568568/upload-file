<!DOCTYPE html>
<html>

<head>

    <script src="https://code.jquery.com/jquery-1.12.4.min.js">
    </script>
    <script>
        $(document).ready(function () {
            $('input[type="file"]').change(function (event) {
                document.getElementById("filepath").value = document.getElementById("uploadfile").files[0].name;
            });

            $("#btnSubmit").click(function (event) {
                //stop submit the form, we will post it manually.
                event.preventDefault();
                fire_ajax_submit();
            });
        });
        function fire_ajax_submit() {
            $.ajax({
                url: "/uploadFile",
                type: "POST",
                data: new FormData($("#upload-file-form")[0]),
                enctype: 'multipart/form-data',
                processData: false,
                contentType: false,
                cache: false,
                success: function () {
                    // Handle upload success
                    alert("File succesfully uploaded");
                },
                error: function () {
                    // Handle upload error
                    alert(
                        "File not uploaded (perhaps it's too much big)");
                }
            });

        }
    </script>
</head>

<body>
<form method="POST" enctype="multipart/form-data" id="upload-file-form">
    <input type="text" id="filepath">
    <input type="file" id="uploadfile" name="uploadfile" style="color:transparent; width:100px;">
    <input type="submit" value="Submit" id="btnSubmit"/>
</form>
</body>

</html>