
$('#CategoryClick').click(function(){
  $('.category-toggle').slideToggle();
})

// function ToggleLi() {
//   $('.dp-content').slideToggle();
// }









const sidebar = document.getElementById('Sidebar-menu');

function OpenNav(){
  if(sidebar.style.width==='70%') {
    sidebar.style.width='0%'
  }
  else{
    sidebar.style.width='70%'
  }
}

// Top Button


// Counter

$('.counter').each(function() {
    var $this = $(this),
        countTo = $this.attr('data-count');
    
    $({ countNum: $this.text()}).animate({
      countNum: countTo
    },
  
    {
  
      duration: 12000,
      easing:'linear',
      step: function() {
        $this.text(Math.floor(this.countNum));
      },
      complete: function() {
        $this.text(this.countNum);
        //alert('finished');
      }
    });  
  });



// Hamburger menu
(function () {
	$('.menu-wrapper').on('click', function() {
		$('.hamburger-menu').toggleClass('animate');
	})
})();


// Search Button Phone
$('#searchBtn').click(function(){
  $('#Search-media').slideToggle();
});

const LefsideBar = document.getElementById('LeftSidebar-menu');
const menu = document.getElementById('menu');

/*
$('.phone-menu').click(() => {
  if(sidebar.style.width==='70%') {
    sidebar.style.width='0%'
  }
  else{
    sidebar.style.width='70%'
  }
})
*/


// LeftSidebar Button
$('#leftSideBar').click(function(){
  if(LefsideBar.style.left==='-80%'){
    LefsideBar.style.left='0%';
  }
  else{
    LefsideBar.style.left='-80%'
  }
});





// Loading Page

// const loadingNumber = document.querySelector('#loadingNumber');
// const loadingCircle = document.querySelector('.loading-circle');
// let load = 0;

// setInterval(updateLoader, 10);

// function updateLoader() {
//   load += (load < 100);
//   loadingNumber.innerHTML = load + "%";
//   loadingCircle.style.background = 'conic-gradient(from 0deg at 50% 50%, rgba(111, 123, 247, 1) 0%, rgba(155, 248, 244, 1) ' + load + '%, #101012 ' + load + '%)'
// }



// function ShowPage() {
//   document.getElementById('all-website').style.display = 'block';
//   document.getElementById('loader').style.display = 'none';
// }

// setInterval(ShowPage, 1000);






// Index Page Slider


