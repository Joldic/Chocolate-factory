import { useEffect, useState } from "react";
import './styles/AddNewForm.css'

function FinishedOrders(){

    const[orders, setOrders] = useState([])
    const[month, setMonth] = useState([])

    var test = JSON.parse(localStorage.getItem('testToken'))

    const handleChoose = () => {
        fetch("http://localhost:8081/api/orders/getFinishedOrders/"+month, {
            headers : {
                'Content-Type': 'application/json',
                'Accept': 'application/json',
                Authorization: `Bearer ${test.accessToken}`,
            },
            method : "GET"
        }).then(res=>res.json()).then((result)=>{
            setOrders(result);
            console.log(result);

            if(result.length === 0){
                document.getElementById('messageTable').hidden=false;
                document.getElementById('ordersTable').hidden=true;
            }else{
                document.getElementById('messageTable').hidden=true;
                document.getElementById('ordersTable').hidden=false;
            }
        });
    }

    return(
        <body>
        <div class="topnav">
            <a href="/ProductionManagerHome">Home</a>
            <a href="/MachinesView">Machines Review</a>
            <a href="/IngredientsView">Ingredients Review</a>
            <a href="/ProductionRequests">Production Requests Review</a>
            <a href="/ProductsView">Products Review</a>
            <a href="/InProductionOrders">Orders in production</a>
            <a href="/IngredientRequests">Ingredient requests</a>
            <a className="active" href="/FinishedOrders">Finished orders</a>
        </div>
        <div className="wrapper" id="chooseDiv">
            <fieldset>
                <p><strong>Finished orders for month</strong></p>
                <select onChange={(e) => {setMonth(e.target.value)}}>
                    <option value="">---Select month</option>
                    <option value="JANUARY">January 2023</option>
                    <option value="FEBRUARY">February 2023</option>
                    <option value="MARCH">March 2023</option>
                    <option value="APRIL">April 2023</option>
                    <option value="MAY">May 2023</option>
                    <option value="JUNE">June 2023</option>
                    <option value="JULY">July 2023</option>
                    <option value="AUGUST">August 2023</option>
                    <option value="SEPTEMBER">September 2023</option>
                    <option value="OCTOBER">October 2023</option>
                    <option value="NOVEMBER">November 2023</option>
                    <option value="DECEMBER">December 2023</option>
                </select><br/>
                <button onClick={handleChoose}>Select</button>
            </fieldset>
        </div>
        <div className="wrapper">
            <table id="messageTable" hidden='true'>
                <tr>
                    <p><strong>No finished orders for selected month.</strong></p>
                </tr>
            </table>
            <table id="ordersTable" hidden='true'>
                <tr>
                    <th>Adderss</th>
                    <th>City</th>
                    <th>Date</th>
                    <th>Total price</th>
                    <th>Total weight</th>
                    <th>Status</th>
                    <th>Products</th>
                    <th>Quantity</th>
                </tr>
                {orders.map((val, key) => {
                    return(
                        <tr>
                            <td>{val.address}</td>
                            <td>{val.city}</td>
                            <td>{val.date[2]+'.'+val.date[1]+'.'+val.date[0]}</td>
                            <td>{val.totalPrice}</td>
                            <td>{val.totalWeight}</td>
                            <td>Finished</td>
                            <td>
                                <div>
                                    {val.orderedProductsDTO.map((val2, key2) => {
                                        return(
                                            <div>
                                                <p>Product: {val2.productDTO.productName}</p>
                                            </div>
                                        )
                                    })}
                                </div>
                            </td>
                            <td>
                                <div>
                                    {val.orderedProductsDTO.map((val3, key3) => {
                                        return(
                                            <div>
                                                <p> {val3.quantity}</p>
                                            </div>
                                        )
                                    })}
                                </div>
                            </td>
                        </tr>
                    )
                })}
                <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td>
                        <button onClick={
                            (e) => {
                                e.preventDefault();
                                localStorage.setItem('month', month);
                                window.location.href="/OrdersReport";
                            }
                        }>Generate report pdf</button>
                    </td>
                </tr>
            </table>
        </div>
        </body>
    )

}
export default FinishedOrders;