
function urlTitle(text) {       
    var characters = [' ', '!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '+', '=', '_', '{', '}', '[', ']', '|', '/', '<', '>', ',', '.', '?', '--']; 

    for (var i = 0; i < characters.length; i++) {
         var char = String(characters[i]);
         text = text.replace(new RegExp("\\" + char, "g"), '-');
    }
    text = text.toLowerCase();
    return text;
}

var createUser = angular.module('createUser', []);
createUser.controller('frmCtrl', function($scope) {});


var createUser = angular.module('createPost', []);

createUser.controller('postCtrl', function($scope) {
	
	
	
	$scope.generateSeo = function() {
		
		console.log("2");
        $scope.seo = "dd";
    };
	
});

