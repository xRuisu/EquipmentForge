// ---- State: 4 sets of IDs ----
const sets = new Map([
  ["PRINTER", new Set()],
  ["PHASAR", new Set()],
  ["RADIO", new Set()],
  ["SCANNER", new Set()],
]);
let currentSet = "PRINTER";

// ---- DOM ----
const setPicker = document.getElementById("setPicker");
const idInput = document.getElementById("idInput");
const addBtn = document.getElementById("addBtn");
const clearCurrentBtn = document.getElementById("clearCurrentBtn");
const clearAllBtn = document.getElementById("clearAllBtn");
const exportBtn = document.getElementById("exportBtn");
const importInput = document.getElementById("importInput");
const printBtn = document.getElementById("printBtn");
const dataTableBody = document.querySelector("#dataTable tbody");
const operatorEl = document.getElementById("operator");
const locationEl = document.getElementById("location");
const dateEl = document.getElementById("date");
const psOperator = document.getElementById("ps-operator");
const psDateLoc = document.getElementById("ps-dateLoc");
const psGrid = document.getElementById("ps-grid");
const psTotal = document.getElementById("ps-total");

// ---- Date Formatting ----
function formatDateDMY(iso) {
  // iso is "YYYY-MM-DD"
  const [year, month, day] = iso.split("-");
  return `${month}/${day}/${year}`;
}

// ---- Init ----
dateEl.valueAsDate = new Date();
buildSetChips();
renderAll();
idInput.focus();

// ---- Set picker ----
function buildSetChips() {
  setPicker.innerHTML = "";
  for (const name of sets.keys()) {
    const chip = document.createElement("div");
    chip.className = "chip" + (name === currentSet ? " active" : "");
    chip.textContent = name;
    chip.tabIndex = 0;
    chip.addEventListener("click", () => {
      currentSet = name;
      updateActiveChips();
      idInput.focus();
    });
    chip.addEventListener("keydown", (e) => {
      if (e.key === "Enter" || e.key === " ") {
        currentSet = name;
        updateActiveChips();
      }
    });
    setPicker.appendChild(chip);
  }
}
function updateActiveChips() {
  [...setPicker.children].forEach((ch) =>
    ch.classList.toggle("active", ch.textContent === currentSet)
  );
}

// ---- Rendering ----
function renderAll() {
  renderTable();
  updateCounts();
}

function renderTable() {
  dataTableBody.innerHTML = "";
  for (const [setName, idSet] of sets) {
    for (const id of idSet) {
      const tr = document.createElement("tr");
      const tdSet = document.createElement("td");
      const tdId = document.createElement("td");
      const tdAct = document.createElement("td");

      tdSet.textContent = setName;
      tdId.textContent = id;

      const rmv = document.createElement("button");
      rmv.textContent = "Remove";
      rmv.title = "Remove this ID";
      rmv.addEventListener("click", () => {
        idSet.delete(id);
        renderAll();
        idInput.focus();
      });

      const move = document.createElement("button");
      move.textContent = "Move…";
      move.title = "Move to a different set";
      move.addEventListener("click", async () => {
        const to = prompt(
          "Move to which set? Example: PRINTER or SCANNER?",
          currentSet
        );
        if (to && sets.has(to)) {
          idSet.delete(id);
          sets.get(to).add(id);
          renderAll();
        } else if (to) {
          alert("Unknown set name.");
        }
      });

      const rowAct = document.createElement("div");
      rowAct.className = "row-actions";
      rowAct.appendChild(rmv);
      rowAct.appendChild(move);
      tdAct.appendChild(rowAct);

      tr.appendChild(tdSet);
      tr.appendChild(tdId);
      tr.appendChild(tdAct);
      dataTableBody.appendChild(tr);
    }
  }
}

function updateCounts() {
  document.getElementById("count1").textContent = sets.get("PRINTER").size;
  document.getElementById("count2").textContent = sets.get("PHASAR").size;
  document.getElementById("count3").textContent = sets.get("RADIO").size;
  document.getElementById("count4").textContent = sets.get("SCANNER").size;
  const total = [...sets.values()].reduce((sum, s) => sum + s.size, 0);
  document.getElementById("totalCount").textContent = total;
}

// ---- Actions ----
function addId() {
  const raw = idInput.value.trim();
  if (!raw) return;
  const id = raw.toUpperCase();
  const set = sets.get(currentSet);
  const before = set.size;
  set.add(id);
  if (set.size !== before) {
    renderAll();
  }
  idInput.value = "";
  idInput.focus();
}

addBtn.addEventListener("click", addId);
idInput.addEventListener("keydown", (e) => {
  if (e.key === "Enter") addId();
});

// Paste handler (scanner-friendly)
idInput.addEventListener("paste", (e) => {
  const txt = (e.clipboardData || window.clipboardData).getData("text");
  if (!txt) return;
  const lines = txt
    .split(/[\r\n]+/)
    .map((s) => s.trim())
    .filter(Boolean);
  if (lines.length > 1) {
    e.preventDefault();
    const set = sets.get(currentSet);
    let changed = false;
    for (const line of lines) {
      const id = line.toUpperCase();
      const before = set.size;
      set.add(id);
      if (set.size !== before) changed = true;
    }
    if (changed) renderAll();
  }
});

clearCurrentBtn.addEventListener("click", () => {
  if (confirm(`Clear ${currentSet}?`)) {
    sets.get(currentSet).clear();
    renderAll();
  }
});
clearAllBtn.addEventListener("click", () => {
  if (confirm("Clear ALL sets?")) {
    for (const s of sets.values()) s.clear();
    renderAll();
  }
});

exportBtn.addEventListener("click", () => {
  const payload = {
    operator: operatorEl.value,
    location: locationEl.value,
    date: dateEl.value,
    sets: Object.fromEntries([...sets.entries()].map(([k, v]) => [k, [...v]])),
  };
  const blob = new Blob([JSON.stringify(payload, null, 2)], {
    type: "application/json",
  });
  const a = document.createElement("a");
  a.href = URL.createObjectURL(blob);
  a.download = `equipmentforge-${new Date().toISOString().slice(0, 10)}.json`;
  a.click();
  URL.revokeObjectURL(a.href);
});

importInput.addEventListener("change", async (e) => {
  const file = e.target.files?.[0];
  if (!file) return;
  const text = await file.text();
  try {
    const obj = JSON.parse(text);
    operatorEl.value = obj.operator || "";
    locationEl.value = obj.location || "";
    dateEl.value = obj.date || new Date().toISOString().slice(0, 10);
    for (const key of ["PRINTER", "PHASAR", "RADIO", "SCANNER"]) {
      sets.set(key, new Set((obj.sets && obj.sets[key]) || []));
    }
    renderAll();
  } catch (err) {
    alert("Invalid JSON file.");
  } finally {
    importInput.value = "";
  }
});

// ---- Printing ----
printBtn.addEventListener("click", () => {
  // Fill print header
  psOperator.value = operatorEl.value || "";
  let d = dateEl.value || new Date().toISOString().slice(0, 10);
  d = formatDateDMY(d);
  const loc = locationEl.value || "";
  psDateLoc.value = `${loc}${d ? " — " + d : ""}`;

  // Fill grid with sets
  psGrid.innerHTML = "";
  let total = 0;
  for (const [setName, idSet] of sets) {
    const box = document.createElement("div");
    box.className = "ps-box";
    const title = document.createElement("div");
    title.className = "title";
    title.textContent = `${setName} (${idSet.size})`;
    box.appendChild(title);
    const ul = document.createElement("ul");
    for (const id of idSet) {
      const p = document.createElement("p");
      p.textContent = `🛠 ${id} ☐ Employee#:____________________________ Returned: ☐ Exchanged: ☐ Comments:_______________________`;
      ul.appendChild(p);
      total++;
    }
    box.appendChild(ul);
    psGrid.appendChild(box);
  }
  psTotal.textContent = total;
  window.print();
});

// Keyboard shortcuts
window.addEventListener("keydown", (e) => {
  if (e.altKey && e.key >= "1" && e.key <= "4") {
    const idx = parseInt(e.key, 10);
    currentSet = `SET ${idx}`;
    updateActiveChips();
    e.preventDefault();
  }
  if ((e.ctrlKey || e.metaKey) && e.key.toLowerCase() === "p") {
    e.preventDefault();
    printBtn.click();
  }
});
