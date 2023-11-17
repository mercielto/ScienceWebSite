<script>
    function removeBtn() {
        var parent = document.getElementById("post-single-comments");
        var child = document.getElementById("comment-submit");
        parent.removeChild(child);
    }

    function submit() {
        var value = document.getElementById("textArea").value;

        var params = new URLSearchParams({
            "text": value,
            "link": window.location.href
        });
        $.ajax({
            url: 'http://localhost:8080/WebProjectScience_war_exploded/ajax/commentPush',
            method: 'post',
            type: "post",
            data: params.toString(),
            success: function(){
                removeBtn();
            }
        });
    }
</script>