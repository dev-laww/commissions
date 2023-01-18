AOS.init({
    duration: 2000,
    once: true,
});

const navMenu = document.querySelector(".nav-menu");
const mobileMenu = document.querySelector(".mobile-menu");

mobileMenu.addEventListener("click", () => {
    document.querySelector("header").classList.toggle("active");
    navMenu.classList.toggle("active");
    mobileMenu.classList.toggle("active");
});

// Navigation
const navBar = document.querySelector("header");
const navLinks = document.querySelectorAll(".nav-link");
const whatsNew = document.querySelector(".whats-new");

const observer = new IntersectionObserver(
    function (entries, observer) {
        entries.forEach((entry) => {
            if (entry.isIntersecting) {
                navBar.classList.add("dark");
                if (window.innerWidth < 768) navMenu.classList.add("dark");
                return;
            }
            navMenu.classList.remove("dark");
            navBar.classList.remove("dark");
        });
    },
    { threshold: 0, rootMargin: "0px 0px -250px 0px" }
);

if (
    document.location.pathname === "/index.html" ||
    document.location.pathname === "/"
)
    observer.observe(whatsNew);

if (document.location !== "/index.html" || document.location !== "/")
    navBar.classList.add("dark");
