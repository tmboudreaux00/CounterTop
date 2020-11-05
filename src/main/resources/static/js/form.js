(() => {
    document.getElementById("createForm").addEventListener("keypress", (e) => {
       let key = e.charCode || e.keyCode || 0;
       if (key === 13) {
           e.preventDefault();
           return false;
       }
    });

    document.getElementById("ingredients").addEventListener("keypress", (e) => {
        let key = e.charCode || e.keyCode || 0;
        const ingredients = document.getElementById("ingredients");

        if (key === 13) {
            const childInput = ingredients.lastElementChild;
            const name = childInput.getAttribute("name");
            const number = parseInt(name.slice("ingredient".length+1, name.length)) + 1;
            const html = `<br><input type="text" name="ingredient-${number}" id="ingredient-${number}">`;
            document.getElementById("ingredient-label").setAttribute("for",`ingredient-${number}`);
            childInput.insertAdjacentHTML("afterend",html);
            const newChild = ingredients.lastElementChild;
            newChild.focus();
        }
    });

    document.getElementById("createForm").addEventListener("submit",(e) => {
        const trueIngredients = document.getElementById("trueIngredients");
        const name = document.getElementById("ingredients").lastElementChild.getAttribute("name");
        const number = parseInt(name.slice("ingredient".length+1, name.length));
        let string = "";
        for(let i=0; i<=number; i++) {
            console.log(document.getElementById(`ingredient-${i}`).value);
            string += document.getElementById(`ingredient-${i}`).value;
            string += " pBREAKq ";
        }
        trueIngredients.value = string;
    });
})()