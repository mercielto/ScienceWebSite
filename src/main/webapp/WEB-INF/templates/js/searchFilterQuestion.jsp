<script>
    function getCheckedCheckBoxes() {
        var checkboxes = document.getElementsByClassName('checkbox');
        var checkboxesChecked = [];
        for (var index = 0; index < checkboxes.length; index++) {
            if (checkboxes[index].checked) {
                checkboxesChecked.push(checkboxes[index].name);
            }
        }
        return checkboxesChecked;
    }

    function isEnterPressedOnTags() {
        var tags = document.getElementById("tags");
        tags.addEventListener("keydown", function (e) {
            if (e.code === "Enter") {
                addTag(e);
            }
        });
    }

    function addTag(e) {
        var tag = tags.value;
        var div = document.getElementById("tags-list");
        if (tag.length !== 0) {
            tags.value = ""
            var p = document.createElement('p');
            p.textContent = tag;
            p.className = "tags-div rounded";
            p.onclick = function deleteTag(e) {
                var parent = document.getElementById("tags-list");
                parent.removeChild(e.currentTarget);
            }
            div.appendChild(p);
        }
    }

    function getTags() {
        var tags = document.getElementsByClassName("tags-div");
        var ans = [];
        for (var index = 0; index < tags.length; index++) {
            ans.push(tags[index].textContent);
        }
        return ans;
    }

    function replaceQuestions(data) {
        var parent = document.getElementById("forum-questions");
        var children = document.getElementsByClassName("forum-questions-single");
        var length = children.length;
        for (var i = 0; i < length; i++) {
            parent.removeChild(children.item(0));
        }
        parent.innerHTML = data;
    }

    function getParams() {
        return new URLSearchParams({
            "dateFrom": document.getElementById("date-from").value,
            "dateTo": document.getElementById("date-to").value,
            "checkBox": JSON.stringify(getCheckedCheckBoxes()),
            "tags": JSON.stringify(getTags()),
            "page": page
        });
    }

    function addPosts(data) {
        var parent = document.getElementById("forum-questions");
        parent.innerHTML += data;
    }

    var page = 1;

    $(document).ready(function (){
        $("#filter").click(function (){
            page = 1;
            const params = getParams();
            $.ajax({
                url: 'http://localhost:8080/WebProjectScience_war_exploded/ajax/settings/questions',
                method: 'post',
                type: "post",
                data: params.toString(),
                success: function(data){
                    replaceQuestions(data);
                }
            });
        })
    });

    $(document).ready(function (){
        $("#need-more").click(function (){
            page++;
            const params = getParams();
            $.ajax({
                url: 'http://localhost:8080/WebProjectScience_war_exploded/ajax/settings/questions',
                method: 'post',
                type: "post",
                data: params.toString(),
                success: function(data){
                    addPosts(data);
                }
            });
        })
    });

    isEnterPressedOnTags();
</script>
