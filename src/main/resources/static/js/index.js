document.addEventListener("DOMContentLoaded", function () {
  function isElementInViewport(el) {
    var rect = el.getBoundingClientRect();
    return (
      rect.top >= 0 &&
      rect.left >= 0 &&
      rect.bottom <=
        (window.innerHeight || document.documentElement.clientHeight) &&
      rect.right <= (window.innerWidth || document.documentElement.clientWidth)
    );
  }

  function handleScrollAnimation() {
    var element = document.getElementById("animatedPirateShip");
    var clickMeDiv = document.getElementById("clickMeDiv");

    if (isElementInViewport(element)) {
      element.classList.add("slide-right");
      element.addEventListener("animationend", function () {
        clickMeDiv.style.display = "block";
        clickMeDiv.classList.add("show-up");
        element.setAttribute("x-on:click", "modelOpen = !modelOpen");
      });

      window.removeEventListener("scroll", handleScrollAnimation);
    }
  }

  window.addEventListener("scroll", handleScrollAnimation);
});
