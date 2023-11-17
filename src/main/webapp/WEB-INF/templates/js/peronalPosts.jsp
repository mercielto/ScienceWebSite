<script>

  function getParams() {
    return  new URLSearchParams({
      "page": page
    });
  }

  function addPosts(data) {
    var parent = document.getElementById("posts_");
    parent.innerHTML += data;
  }

  var page = 1;

  $(document).ready(function (){
    $("#need-more").click(function (){
      page++;
      const params = getParams();
      $.ajax({
        url: 'http://localhost:8080/WebProjectScience_war_exploded/ajax/personal-posts',
        method: 'post',
        type: "post",
        data: params.toString(),
        success: function(data){
          addPosts(data);
        }
      });
    })
  });
</script>