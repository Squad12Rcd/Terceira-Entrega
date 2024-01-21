function showPassword () {
    let inputPass = document.getElementById('password')
    let btnShowPass = document.getElementById('btn-password')

    if(inputPass.type === 'password') {
        inputPass.setAttribute('type','text')
        btnShowPass.classList.replace('fa-eye','fa-eye-slash')
    } else {
        inputPass.setAttribute('type','password')
        btnShowPass.classList.replace('fa-eye-slash','fa-eye')
    }
}

function cpfMask() {
    const cpfInput = document.getElementById('cpf')
    let cpfInputLength = cpfInput.value.length
    if (cpfInputLength === 3 || cpfInputLength === 7) {
        cpfInput.value += '.'
    } else if(cpfInputLength === 11) {
        cpfInput.value += '-'
    }
}

function cnpjMask() { 
    const cnpjInput = document.getElementById('cnpj')
    let cnpjInputLength = cnpjInput.value.length
    if (cnpjInputLength === 2 || cnpjInputLength === 6) {
        cnpjInput.value += '.'
    } else if(cnpjInputLength === 10) {
        cnpjInput.value += '/'
    } else if(cnpjInputLength === 15) {
        cnpjInput.value += '-'
    }
}


const carregarVideos = () => {

    const dataAtt = document.querySelectorAll("[data]");
    
    dataAtt.forEach((elemento) => {
    if (elemento.getBoundingClientRect().top < window.innerHeight) {
        elemento.src = elemento.getAttribute("data");
        elemento.removeAttribute("data")
    }
})
};
    
 
window.onscroll = () => {
    carregarVideos()
    };

// HOME 

    var lastScrollTop = 0;
    

    function getScrollDirection() {
      var st = window.pageYOffset || document.documentElement.scrollTop;
    
      if (st > lastScrollTop) {

        return 'down';
      } else {

        return 'up';
      }
    
      lastScrollTop = st <= 0 ? 0 : st; 
    }
    
 
    function handleScroll() {
      var contents = document.querySelectorAll('.transicao');
      var scrollDirection = getScrollDirection();
    
      contents.forEach(function(content) {

        if (content.getBoundingClientRect().top < window.innerHeight && content.getBoundingClientRect().bottom >= 0) {

          content.classList.add('fade-in');
        } else {

          content.classList.remove('fade-in');
        }
      });
    }
    

    window.addEventListener('scroll', handleScroll);
    
    handleScroll();


const text = "Uma das médias salariais mais altas do país.";
const typingEffectElement = document.getElementById("typing");

function typeWriter(text, i, cb) {
  if (i < text.length) {
    typingEffectElement.innerHTML += text.charAt(i);
    i++;
    setTimeout(function() {
      typeWriter(text, i, cb);
    }, 120); 
  } else {
    if (cb) cb();
  }
}

typeWriter(text, 0);

//dark-mode
function toggleDarkMode() {
  const body = document.body;
  body.classList.toggle('dark-mode');
}

console.log('Botão Modo Leitura clicado!');
