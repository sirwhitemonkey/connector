$(function() {
  var springfox = {
    "baseUrl": function() {
      var urlMatches = /(.*)\/api.*/.exec(window.location.href);
      return urlMatches[1];
    },
    "securityConfig": function(cb) {
      $.getJSON(this.baseUrl() + "/configuration/security", function(data) {
        cb(data);
      });
    },
    "uiConfig": function(cb) {
      $.getJSON(this.baseUrl() + "/configuration/ui", function(data) {
        cb(data);
      });
    }
  };
  window.springfox = springfox;
  window.oAuthRedirectUrl = springfox.baseUrl() + '/webjars/springfox-swagger-ui/o2c.html'

  $('#select_baseUrl').change(function() {
    window.swaggerUi.headerView.trigger('update-swagger-ui', {
      url: $('#select_baseUrl').val()
    });
  });

  $('#oauthToken').click(function() {
	  var oauth ="eCommerce-Connector-Client:eCommerceConnector";
	  
	  $.ajax({
		    url: springfox.baseUrl() + '/oauth/token',
		    headers: {
		        'Authorization':'Basic ' + base64encode(oauth),
		        'Content-Type':'application/x-www-form-urlencoded'
		    },
		    type: 'POST',
		    data: {
		    	grant_type: "password",
		    	username: $('#username').val(),
		    	password: $('#password').val()
		    },
		    success: function(data){
		    	alert("OAUTH2 {Success}\n\n" + JSON.stringify(data));
		    },
		    error: function(data) {
		    	alert("OAUTH2 {Failure}\n\n" + JSON.stringify(data));
		    }
		    
		  });
	 
  });
  
  $('#oauthRefreshToken').click(function() {
var oauth ="eCommerce-Connector-Client:eCommerceConnector";
	  
	  var refreshToken = prompt("Current refresh token", "");
	  if (refreshToken != null && refreshToken !== "") {
		  $.ajax({
			    url: springfox.baseUrl() + '/oauth/token',
			    headers: {
			        'Authorization':'Basic ' + base64encode(oauth),
			        'Content-Type':'application/x-www-form-urlencoded'
			    },
			    type: 'POST',
			    data: {
			    	grant_type: "refresh_token",
			    	refresh_token: refreshToken,
			    	username: $('#username').val(),
			    	password: $('#password').val()
			    },
			    success: function(data){
			    	alert("OAUTH2 {Success}\n\n" + JSON.stringify(data));
			    },
			    error: function(data) {
			    	alert("OAUTH2 {Failure}\n\n" + JSON.stringify(data));
			    }
			  });
	  }
  });
	 
  
  function maybePrefix(location, withRelativePath) {
    var pat = /^https?:\/\//i;
    if (pat.test(location)) {
      return location;
    }
    return withRelativePath + location;
  }

  $(document).ready(function() {
    var relativeLocation = springfox.baseUrl();

     $.getJSON(relativeLocation + "/swagger-resources", function(data) {

      var $urlDropdown = $('#select_baseUrl');
      $urlDropdown.empty();
      $.each(data, function(i, resource) {
        var option = $('<option></option>')
            .attr("value", maybePrefix(resource.location, relativeLocation))
            .text(resource.name + " (" + resource.location + ")");
        $urlDropdown.append(option);
      });
      $urlDropdown.change();
    });

  });

});
