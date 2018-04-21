<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

 <div id="map" style = "height: 500px; width : 500px; ">
    <script>
      function initMap() {
    	  var location = {lat: 42.697, lng: 23.324};
       
        var map = new google.maps.Map(document.getElementById('map'), {
          zoom: 11.5,
          center: location
        });
        
        function addMarker(properties){
        	var marker = new google.maps.Marker({
            	position:properties.coordinates,
            	map:map
        	});
            if(properties.content){
            	var infoWindow = new google.maps.InfoWindow({
            		content:properties.content
            	});
            			
      			marker.addListener('click',function(){
      				infoWindow.open(map,marker);
      			});
            }
        }
        
        addMarker({
        		coordinates:{lat:42.649,lng:23.408},
        		content:'<h3>Domino\'s</h3><h6>бул. „проф. Цветан Лазаров“ 48, 1582 ж.к. Дружба 2, София</h6>'
        });
        
        addMarker({
    		coordinates:{lat:42.653,lng:23.346},
    		content:'<h3>Domino\'s</h3><h6>ул. „Йордан Йосифов“ 4, 1700 Студентски Комплекс, София</h6>'
   		 });
       
        addMarker({
    		coordinates:{lat:42.678,lng:23.364},
    		content:'<h3>Domino\'s</h3><h6> бул. „Шипченски проход“ 1113, 1113 Гео Милев, София</h6>'
   		 });
        
        addMarker({
    		coordinates:{lat:42.672,lng:23.285},
    		content:'<h3>Domino\'s</h3><h6>ул. „ген. Стефан Тошев“ 8, 1680 ж.к. Красно село - кв. Борово, София</h6>'
   		 });
        
        addMarker({
    		coordinates:{lat:42.680,lng:23.356},
    		content:'<h3>Domino\'s</h3><h6>ул. „Александър Жендов“ 6, 1113 Гео Милев, София</h6>'
   		 });
        
        addMarker({
    		coordinates:{lat:42.707,lng:23.288},
    		content:'<h3>Domino\'s</h3><h6>ул. „Цар Симеон“ 271, 1309 ж.к. Илинден, София</h6>'
   		 });
        
        addMarker({
    		coordinates:{lat:42.697,lng:23.317},
    		content:'<h3>Domino\'s</h3><h6>бул. Александър Стамболийски 41, 1532 София</h6>'
   		 });
                
        addMarker({
    		coordinates:{lat:42.636,lng:23.369},
    		content:'<h3>Domino\'s</h3><h6>бул. „Александър Малинов“ 78, 1712 в.з. Американски колеж, София</h6>'
   		 });
        
        addMarker({
    		coordinates:{lat:42.674,lng:23.307},
    		content:'<h3>Domino\'s</h3><h6>улица „Бяла черква“, 1407 София</h6>'
   		 });
        
        addMarker({
    		coordinates:{lat:42.665,lng:23.319},
    		content:'<h3>Domino\'s</h3><h6>бул. „Никола Й. Вапцаров“ 6, 1407 ж.к. Лозенец, София</h6>'
   		 });
        
        addMarker({
    		coordinates:{lat:42.703,lng:23.356},
    		content:'<h3>Domino\'s</h3><h6>ж.к. Сухата река 12-Е,, 1505 София</h6>'
   		 });
        
        addMarker({
    		coordinates:{lat:42.734,lng:23.294},
    		content:'<h3>Domino\'s</h3><h6>бул. „Ломско шосе“ 174, 1231 ж.к. Надежда 4, София</h6>'
   		 });

        addMarker({
    		coordinates:{lat:42.661,lng:23.264},
    		content:'<h3>Domino\'s</h3><h6>ул. „Александър Пушкин“ 38, 1000 ж.к. Бъкстон, София</h6>'
   		 });

      }
      
    </script>
    <script src="https://maps.googleapis.com/maps/api/js?key= AIzaSyB9OVx6nYiXHnqLDdCxPqx0TYenbqCGv2o&callback=initMap"
    async defer></script>
    </div>