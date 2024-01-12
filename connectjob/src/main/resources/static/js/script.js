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
    console.log("escrollou!");
    };

// HOME 

    var lastScrollTop = 0;
    
    // Função para verificar a direção da rolagem
    function getScrollDirection() {
      var st = window.pageYOffset || document.documentElement.scrollTop;
    
      if (st > lastScrollTop) {
        // Rolagem para baixo
        return 'down';
      } else {
        // Rolagem para cima
        return 'up';
      }
    
      lastScrollTop = st <= 0 ? 0 : st; // Para casos em que o scroll é rápido demais
    }
    
    // Função para lidar com a rolagem da página
    function handleScroll() {
      var contents = document.querySelectorAll('.transicao');
      var scrollDirection = getScrollDirection();
    
      // Itera sobre todos os elementos .content
      contents.forEach(function(content) {
        // Verifica se o conteúdo está visível na janela de visualização e a direção da rolagem
        if (content.getBoundingClientRect().top < window.innerHeight && content.getBoundingClientRect().bottom >= 0) {
          // Adiciona a classe para fazer o conteúdo aparecer com fade in
          content.classList.add('fade-in');
        } else {
          // Remove a classe se o conteúdo estiver fora da visualização
          content.classList.remove('fade-in');
        }
      });
    }
    
    // Adiciona um event listener para lidar com a rolagem da página
    window.addEventListener('scroll', handleScroll);
    
    // Chama a função handleScroll() uma vez para verificar se o conteúdo já está visível na página ao carregar
    handleScroll();


const text = "Uma das médias salariais mais altas do país.";
const typingEffectElement = document.getElementById("typing");

function typeWriter(text, i, cb) {
  if (i < text.length) {
    typingEffectElement.innerHTML += text.charAt(i);
    i++;
    setTimeout(function() {
      typeWriter(text, i, cb);
    }, 120); // Ajuste o tempo de digitação aqui (em milissegundos)
  } else {
    if (cb) cb();
  }
}

typeWriter(text, 0);

