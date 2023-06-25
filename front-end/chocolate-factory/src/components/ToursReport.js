import { useEffect, useState } from "react";
import Html from 'react-pdf-html';
import {Document, Page, PDFViewer, StyleSheet} from '@react-pdf/renderer';
import ReactDOMServer from 'react-dom/server';  
import { color, padding } from "@mui/system";

export default function ToursReport(){

    const[orders, setOrders] = useState([]);
    var test = JSON.parse(localStorage.getItem('testToken'))
    var totalPrice = 0;

    var month = localStorage.getItem('month');
    useEffect(() => {
        fetch("http://localhost:8081/api/orders/getDeliveredOrders/"+month, {
            headers : {
                'Content-Type': 'application/json',
                'Accept': 'application/json',
                Authorization: `Bearer ${test.accessToken}`,
            },
            method : "GET"
        }).then(res=>res.json()).then((result)=>{
            setOrders(result);              
            console.log(result);      
        });
    }, []);

    const element = ( 
        <html>
            <body style={{fontSize: 12, padding: 3+'rem'}}>
            <div>
            <h1>Complany</h1>
                Chocolate factory Novi Sad<br/>
                Industrijska zona sever bb, Novi Sad 21000<br/>
                Tel: 021/569223<br/>
                Email: bestchocolateNoviSad@gmail.com<br/>

            <h3>For month: {month}</h3>

            <h3>Orders</h3>
            <table style={{border: 1+'px'}}>
                <thead>
                <tr>
                   <th>Order code</th> 
                   <th>Address</th>
                   <th>City</th>
                   <th>Date</th>
                   <th>Weight</th>
                   <th>Price</th>
                </tr>
                </thead>
                {orders.map((val, key) => {
                    totalPrice+=val.totalPrice;
                    return(
                        <tr>
                            <td>#{key}</td>
                            <td>{val.address}</td>
                            <td>{val.city}</td>
                            <td>{val.date[2]+'.'+val.date[1]+'.'+val.date[0]}</td>
                            <td>{val.totalWeight}RSD</td>
                            <td>{val.totalPrice}RSD</td>
                        </tr>
                    )
                })}
                <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td>Total price: </td>
                    <td>{totalPrice}RSD</td>
                </tr>
            </table>
            </div>
            </body>
        </html>
    )

    const html = ReactDOMServer.renderToStaticMarkup(element);

  const styles = StyleSheet.create({
    page: {
      backgroundColor: "white",
      color: "black",
    },
    section: {
      margin: 10,
      padding: 10,
    },
    viewer: {
      width: window.innerWidth,
      height: window.innerHeight,
    },
  });

  return(
    <PDFViewer style={styles.viewer}>
      <Document>
      <Page size="A4" style={styles.page}>
        <Html>{html}</Html>
      </Page>
    </Document>
    </PDFViewer>
  )
}