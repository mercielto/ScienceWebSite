<script>
  function deleteObject(event) {
    if (confirm("Are you sure?")) {
      var child = event.target.parentElement.parentElement;
      var parent = child.parentElement;

      var a = child.getElementsByClassName("my-posts-title").item(0);
      var link = a.getAttribute("href");
      var params = new URLSearchParams({
        "link": link
      });

      $.ajax({
        url: 'http://localhost:8080/WebProjectScience_war_exploded/ajax/myQuestions',
        method: 'post',
        type: "post",
        data: params.toString(),
        success: function(data){
          parent.removeChild(child);
        }
      });
    }
  }

</script>
