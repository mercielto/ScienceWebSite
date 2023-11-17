<script>
    function deleteImg() {
        var img = document.getElementById("prof-photo");
        var input = document.getElementById("img-input");
        $.ajax({
            url: 'http://localhost:8080/WebProjectScience_war_exploded/ajax/getDefaultProfileImage',
            method: 'post',
            type: "post",
            success: function(data){
                img.src = data;
                input.files.add(data);
            }
        });
    }
</script>