//Masks
$("#inputTelefone").mask("(00) 00000-0000");

var alunos = [];
var cursos = [];

//save
function save() {
  console.log(document.getElementById("selectCurso").value);
  var alun = {
    id: alunos.length + 1,
    name: document.getElementById("inputNome").value,
    email: document.getElementById("inputEmail").value,
    phone: document.getElementById("inputTelefone").value,
    idCourse: document.getElementById("selectCurso").value,
    period: document.querySelector("input[name='turnoRadios']:checked").value,
  };

  $.ajax({
    url: "http://localhost:8080/students",
    type: "POST",
    contentType: "application/json",
    data: JSON.stringify(alun),
    success: (savedPro) => {
      addNewRow(savedPro);
      alunos.push(savedPro);
      document.getElementById("formAlunos").reset();
    },
  });
}

//on load
loadAlunos();
loadCursos();

function loadCursos() {
  $.ajax({
    url: "http://localhost:8080/courses",
    type: "GET",
    async: false,
    success: (response) => {
      cursos = response;
      for (var cur of cursos) {
        document.getElementById(
          "selectCurso"
        ).innerHTML += `<option value=${cur.id}>${cur.name}</option>`;
      }
    },
  });
}

async function loadAlunos() {
  $.ajax({
    url: "http://localhost:8080/students",
    type: "GET",
    async: true,
    success: (response) => {
      alunos = response;
      for (let alu of alunos) {
        addNewRow(alu);
      }
    },
  });
}

function addNewRow(alu) {
  var table = document.getElementById("alunosTable");

  var row = table.insertRow();

  //insert id
  var idNode = document.createTextNode(alu.id);
  row.insertCell().appendChild(idNode);

  //insert nome
  var nameNode = document.createTextNode(alu.name);
  row.insertCell().appendChild(nameNode);

  //insert email
  var emailNode = document.createTextNode(alu.email);
  var emailCell = row.insertCell();
  emailCell.className = "d-none d-md-table-cell";
  emailCell.appendChild(emailNode);

  //insert telefone
  var telefoneNode = document.createTextNode(alu.phone);
  var telefoneCell = row.insertCell();
  telefoneCell.className = "d-none d-md-table-cell";
  telefoneCell.appendChild(telefoneNode);

  //insert curso
  var cursoNode = document.createTextNode(cursos[alu.idCourse - 1].name);
  var cursoCell = row.insertCell();
  cursoCell.className = "d-none d-md-table-cell";
  cursoCell.appendChild(cursoNode);

  //insert turno
  var turnoNode = document.createTextNode(alu.period);
  var turnoCell = row.insertCell();
  turnoCell.className = "d-none d-md-table-cell";
  turnoCell.appendChild(turnoNode);
}
