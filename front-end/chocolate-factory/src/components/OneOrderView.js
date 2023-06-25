import './styles/AddNewForm.css'
import { useState, useEffect } from 'react'

function ViewOneOrder(){

    const[order, setOrder] = useState([])
    const[user, setUser] = useState([])
    const[products, setProducts] = useState([])
    const[date, setDate] = useState([])

    useEffect(() =>{

        var test = JSON.parse(localStorage.getItem('testToken'))
        var id = localStorage.getItem('orderId');

        fetch("http://localhost:8081/api/orders/"+id, {
                headers : {
                    'Content-Type': 'application/json',
                    'Accept': 'application/json',
                    Authorization: `Bearer ${test.accessToken}`,
                },
                method : "GET"
        }).then(res=>res.json()).then((result)=>{
                setOrder(result);
                setUser(result.userDTO);
                setProducts(result.orderedProductsDTO);
                setDate(result.date);
        }).catch((err) => {
            console.log(err);
        });
        
    }, []);
    
    return(
        <body>
            <div className="topnav">
                <a href="/DeliveryManagerHome">Home</a>
                <a href="/DriversView">Drivers Review</a>
                <a href="/TrucksView">Trucks Review</a>
                <a class="active" href="/OrdersView">Completed Orders Review</a>
                <a href="/DeliveredOrdersView">Delivered Orders Review</a>
                <a href="/EngagementsView">Engagements Review</a>
                <a href='/ViewPlanForTomorrow'>Plan For Tomorrow Review</a>
                <a href="/ViewPlanForToday">Plan For Today Review</a>
                <a href="/">Logout</a>
            </div> 
            <div className='wrapper'>
                <h1>Selected Order</h1>
            </div>
            <div className='wrapper'>
                <table>
                    <tr>
                        <th>Basic Information</th>
                        <th>User</th>
                        <th>Ordered Products</th>
                    </tr>
                    <tr>
                        <td>
                            <p>Address: {order.address}</p>
                            <p>City: {order.city}</p>
                            <p>Date: {date[2]+"."+date[1]+"."+date[0]}</p>
                            <p>Total price: {order.totalPrice}</p>
                            <p>Total weight: {order.totalWeight}</p>
                        </td>
                        <td>
                            <p>First name: {user.firstName}</p>
                            <p>Last name: {user.lastName}</p> 
                            <p>Email: {user.email}</p> 
                            <p>Username: {user.username}</p>  
                        </td>
                        <td>
                            {products.map((val, key) => {
                                return(
                                    <div>
                                        <p><strong>Product: </strong> Product name: {val.productDTO.productName}</p>
                                        <p>Price: {val.productDTO.price}</p>
                                        <p>Weight: {val.productDTO.weight}</p>
                                        <p>Quantity: {val.quantity}</p>
                                    </div>
                                )
                            })}
                        </td>
                    </tr>
                </table>
            </div>
        </body>
    )

}
export default ViewOneOrder;