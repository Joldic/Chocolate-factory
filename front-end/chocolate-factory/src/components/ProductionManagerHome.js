import './styles/AddNewForm.css'
import ProductionRequests from "./ProductionRequests";

function ProductionManagerHome(){

    return(
        <body>
        <div class="topnav">
            <a class="active" href="/ProductionManagerHome">Home</a>
            <a href="/MachinesView">Machines Review</a>
            <a href="/IngredientsView">Ingredients Review</a>
            <a href="/ProductionRequests">Production Requests Review</a>
            <a href="/ProductsView">Products Review</a>
            <a href="/InProductionOrders">Orders in production</a>
            <a href="/IngredientRequests">Ingredient requests</a>
            <a href="/FinishedOrders">Finished orders</a>
        </div>
        </body>
    )
}

export default ProductionManagerHome;