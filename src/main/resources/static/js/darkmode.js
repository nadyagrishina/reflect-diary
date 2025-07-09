document.addEventListener('DOMContentLoaded', () => {
    const toggleBtn = document.getElementById("toggleDarkMode");
    if (!toggleBtn) return;

    if (localStorage.getItem("darkMode") === "true") {
        document.body.classList.add("dark-mode");
    }

    updateIcon();

    toggleBtn.addEventListener("click", () => {
        const isDark = document.body.classList.toggle("dark-mode");
        localStorage.setItem("darkMode", isDark);
        updateIcon();
    });

    function updateIcon() {
        const isDark = document.body.classList.contains("dark-mode");
        toggleBtn.textContent = isDark ? "☀️" : "🌓";
    }
});
