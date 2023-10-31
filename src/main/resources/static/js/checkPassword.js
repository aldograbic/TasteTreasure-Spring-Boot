const password = document.getElementById("password");
const confirmPassword = document.getElementById("confirmPassword");
const passwordWarning = document.getElementById("passwordWarning");

confirmPassword.addEventListener("input", () => {
  if (password.value !== confirmPassword.value) {
    passwordWarning.className = "text-red-600 font-semibold";
  } else {
    passwordWarning.className = "text-black";
  }
});
