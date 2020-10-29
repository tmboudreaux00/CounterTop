(() => {
    const render = (recipe) => {
        const html =
            `<div class="card mb-3" style="width: 18rem;">
                <div class="card-body">
                    <h5 class="card-title">${recipe.title}</h5>
                    <a href="#" class="btn btn-primary">View Recipe</a>
                </div>
            </div>`

        const container = document.getElementById("api-results");
        container.insertAdjacentHTML("beforeend",html);
    }

    const getQueryString = () => {
        const queryString = window.location.search;
        const urlParams = new URLSearchParams(queryString);

        return urlParams.get("searchTerm");
    }

    const getApiResults = async () => {
        const key = "5af6024d199b481f99458dc8fc697543";
        const query = await getQueryString();

        if (query !== null) {
            let result;
            try {
                result = await fetch(`https://api.spoonacular.com/food/products/search?query=${query}&apiKey=${key}`)
                const data = await result.json();
                console.log(data);
                data.products.forEach(e => render(e));
            } catch(error) {
                console.log(error);
            }
        }
    }
    getApiResults();
})();