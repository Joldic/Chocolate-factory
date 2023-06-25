import { useEffect, useState} from "react";
import './styles/AddNewForm.css'
import Html from 'react-pdf-html';
import {Document, Page, PDFViewer, StyleSheet, View, Text} from '@react-pdf/renderer';
import ReactDOMServer from 'react-dom/server';  

export default function DriverPDF(){
    const [tours, setTours] = useState([]);
    const [driver, setDriver] = useState([]);

    var test = JSON.parse(localStorage.getItem('testToken'))
    var driverId = localStorage.getItem('driverId');
    var month = localStorage.getItem('month');

    useEffect(() => {
        fetch("http://localhost:8081/api/tours/getToursByDriver/"+driverId+"/"+month, {
          headers : {
            'Content-Type': 'application/json',
            'Accept': 'application/json',
            Authorization: `Bearer ${test.accessToken}`,
          },
          method : "GET"
        }).then(res=>res.json()).then((result)=>{
          setTours(result);
          console.log(result)

          if(result.length === 0){
            window.alert('No information.');
            window.location.href="/DriversView";
          }
        });

        fetch("http://localhost:8081/api/drivers/"+driverId, {
            headers : {
                'Content-Type': 'application/json',
                'Accept': 'application/json',
                Authorization: `Bearer ${test.accessToken}`,
              },
              method : "GET"
            }).then(res=>res.json()).then((result)=>{
              setDriver(result);
            });
      }, []);

      const date = new Date()
      const element = (<html>
        <body style={{fontSize: 12, padding: 3+'rem'}}>
          <div id="report">
          <h1>Company</h1>
          Chocolate factory Novi Sad<br/>
          Industrijska zona sever bb, Novi Sad 21000<br/>
          Tel: 021/569223<br/>
          Email: bestchocolateNoviSad@gmail.com<br/>
  
          <h2>Driver</h2>
          {driver.firstName+" "+driver.lastName+" ("+driver.jmbg+")"}<br/>
          Tel: {driver.phoneNumber}<br/>
          Address: {driver.adress}<br/>
          Email: {driver.email}<br/>
  
          <h3>Tours</h3>
          <table style={{border: 1+'px'}}>
            <thead>
              <tr>
                <th>City</th>
                <th>Date</th>
              </tr>
            </thead>
            {tours.map((val, key) => {
              return(
                <tr>
                  <td>{val.city}</td>
                  <td>{val.date[2]+"."+val.date[1]+"."+val.date[0]}</td>
                </tr>
              )
            })}
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