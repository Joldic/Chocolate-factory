import './styles/AddNewForm.css'
import { useEffect, useState } from 'react'

function OneProductionRequestReview(){

    const[drivers, setDrivers] = useState([])

    const [active, setActive] = useState(false);

    const [buttonText, setButtonText] = useState('Check')
    const [startButtonText, setStartButtonText] = useState('Start Production')

    const[recipes, setRecipes] = useState([])
    const[warehouse, setWarehouses] = useState([])

    const [path, setPath] = useState('')


    var test = JSON.parse(localStorage.getItem('testToken'))
    var id = localStorage.getItem('productRequestId')

    useEffect(() => {
        fetch("http://localhost:8081/api/orders/getOrderedProductsByOrder/" + id , {
            headers : {
                'Content-Type': 'application/json',
                'Accept': 'application/json',
                Authorization: `Bearer ${test.accessToken}`,
            },
            method : "GET"
        }).then(res=>res.json()).then((result)=>{
            setDrivers(result);
        }).catch((err) =>{
            console.log(err);
        });
            fetch("http://localhost:8081/api/products/allProductsRecipes", {
                headers : {
                    'Content-Type': 'application/json',
                    'Accept': 'application/json',
                    Authorization: `Bearer ${test.accessToken}`,
                },
                method : "GET"
            }).then(res=>res.json()).then((result)=>{
                setRecipes(result);
            }).catch((err) =>{
                console.log(err);
            });
    }, []);

    const navigateToAddNew = (e) =>{
        e.preventDefault()
        //window.location.href = "/AddIngredient"
    }
    const handleClick = (e) => {
        e.preventDefault()
        let i = 0;
        let str = ''
        while(i < drivers.length){
            console.log(drivers[i].productName + ' ' + drivers[i].quantity);
            str += drivers[i].productName + '/' + drivers[i].quantity;
            if(i != drivers.length-1){
                str += "/"
            }
            i++;
        }
        setPath(str);
        str += "/" + localStorage.getItem('productRequestId')
        setPath(str);


        fetch("http://localhost:8081/api/orders/" + str, {
            headers : {
                'Content-Type': 'application/json',
                'Accept': 'application/json',
                Authorization: `Bearer ${test.accessToken}`,
            },
            method : "GET"
        }).then(res=>res.json()).then((result)=>{
            console.log(result);
            setWarehouses(result);

            if(result[0].status == true){
                setButtonText("There Is Enough Ingredients In Storage")
                setActive(!false)
            }else{
                setButtonText('Not Enough Ingredients In Storage')
                setActive(false)
            }
        });



    };

    const startClick = (e) => {
        e.preventDefault()
        console.log(path)
        //let str = path + "/" + localStorage.getItem('productRequestId')
        let str = path
        //setPath(path + "/" + localStorage.getItem('productRequestId'))
        console.log(str)


        fetch("http://localhost:8081/api/orders/" + str, {
            headers : {
                'Content-Type': 'application/json',
                'Accept': 'application/json',
                Authorization: `Bearer ${test.accessToken}`,
            },
            method : "POST"
        }).then(res=>res.json()).then((result)=>{
            console.log(result);

            if(result == true){
                setStartButtonText("Production started successfully")
                setActive(!false)
            }else{
                setStartButtonText('There are no available machines!')
                setActive(false)
            }
        });

    };

    return(

        <body>
        <div class="topnav">
            <a href="/ProductionManagerHome">Home</a>
            <a href="/MachinesView">Machines Review</a>
            <a  href="/IngredientsView">Ingredients Review</a>
            <a class="active"  href="/ProductionRequests">Production Requests Review</a>
            <a href="/ProductsView"> Products Review</a>
            <a href="/InProductionOrders">Orders in production</a>
            <a href="/IngredientRequests">Ingredient requests</a>
        </div>
        <div className='wrapper'>
            <h1>Products request</h1>
        </div>
        <div className='wrapper'>
            <table>
                <tr>
                    <th>Name</th>
                    <th>Quantity</th>
                    <th></th>
                </tr>
                {drivers.map((val, key) => {
                    return(
                        <tr key={key} >
                            <td>{val.productName}</td>
                            <td>{val.quantity}</td>
                        </tr>
                    )
                })}
            </table>

        </div>
        <br/>
        <div className='wrapper'>
            <table>
                <caption><b>Recipes</b></caption>
                <tr>
                    <th>Name</th>
                    <th>Recipe</th>
                    <th></th>
                </tr>
                {recipes.map((val, key) => {
                    return(
                        <tr key={key} >
                            <td>{val.productName}</td>
                            <td>
                                {
                                    val.ingredientDTOList.map((val2, key2)=>{

                                        return(
                                            <div>
                                                <p>Name: <strong>{val2.name} </strong>   Quantity: <strong> {val2.quantity} </strong> </p>
                                            </div>
                                        )

                                    })}
                            </td>
                        </tr>
                    )
                })}
            </table>
        </div>


        <div className='wrapper'>
            <table>
                <caption> <b> Warehouse </b> </caption>
                <tr>
                    <th>Ingredient</th>
                    <th>Quantity</th>
                    <th></th>
                </tr>
                {warehouse.map((val, key) => {
                    return(
                        <tr key={key} >
                            <td>{val.ingredientName}</td>
                            <td>{val.quantity}</td>
                        </tr>
                    )
                })}
            </table>

        </div>

        <div class='wrapper'>
            <button
                onClick={handleClick}
                style={{ backgroundColor: active ? "green" : "red"}}
            >
                {buttonText}
            </button>
        </div>

        <div className='wrapper'>
            <button
                onClick={startClick}
                style={{ backgroundColor: active ? "green" : "red"}}
            >
                {startButtonText}
            </button>
        </div>
        </body>
    )
}
export default OneProductionRequestReview;