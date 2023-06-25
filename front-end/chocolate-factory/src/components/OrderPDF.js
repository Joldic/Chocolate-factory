import { useEffect, useRef, useState} from "react";
import './styles/AddNewForm.css'
import Html from 'react-pdf-html';
import {Document, Page, PDFViewer, StyleSheet, View, Text} from '@react-pdf/renderer';
import ReactDOMServer from 'react-dom/server';  

function BasicDocument(){

    const [order, setOrder] = useState([])
    const [user, setUser] = useState([])
    const [products, setProducts] = useState([])
    const [date, setDate] = useState([])
    var test = JSON.parse(localStorage.getItem('testToken'))

    var orderId = localStorage.getItem("orderId")
    useEffect(() => {
      fetch("http://localhost:8081/api/orders/"+orderId, {
        headers : {
          'Content-Type': 'application/json',
          'Accept': 'application/json',
          Authorization: `Bearer ${test.accessToken}`,
        },
        method : "GET"
      }).then(res=>res.json()).then((result)=>{
        setOrder(result);
        setDate(result.date);
        setUser(result.userDTO);
        setProducts(result.orderedProductsDTO)
      });
    }, []);

    const element = (<html>
      <body style={{fontSize: 12, padding: 3+'rem'}}>
        <div id="report">
        <h1>Invoice</h1>
        Chocolate factory Novi Sad<br/>
        Industrijska zona sever bb, Novi Sad 21000<br/>
        Tel: 021/569223<br/>
        Email: bestchocolateNoviSad@gmail.com<br/>

        <h2>Bill to</h2>
        {user.firstName+" "+user.lastName}<br/>
        {order.address+", "+order.city}<br/>

        <div>
          Date:#{date[2]+"."+date[1]+"."+date[0]}<br/>
        </div>
        <h3>Order</h3>
        <table style={{border: 1+'px'}}>
          <thead>
            <tr>
              <th>Product</th>
              <th>Quantity</th>
              <th>Weight</th>
              <th>Unit Price</th>
              <th>Total Price</th>
            </tr>
          </thead>
          {products.map((val, key) => {
            return(
              <tr>
                <td>{val.productDTO.productName}</td>
                <td>{val.quantity}</td>
                <td>{val.productDTO.weight}</td>
                <td>{val.productDTO.price+"RSD"}</td>
                <td>{parseInt(val.quantity)*parseInt(val.productDTO.price)+"RSD"}</td>
              </tr>
            )
          })}
          <tr>
              <td></td>
              <td></td>
              <td></td>
              <td><strong>Total:</strong></td>
              <td>{order.totalPrice+"RSD"}</td>
          </tr>
        </table>
        </div>
      </body>
    </html>)

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

export default BasicDocument;