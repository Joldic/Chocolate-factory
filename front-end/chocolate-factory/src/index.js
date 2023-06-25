import React from 'react';
import ReactDOM from 'react-dom';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import Login from './components/Login';
import UserRegistration from './components/UserRegistration';
import RegularUserHomepage from './components/RegularUserHomepage';
import ManagerRegistration from './components/ManagerRegistration';
import UserProfileUpdate from './components/UserProfileUpdate';
import DriverCreate from './components/DriverCreate';
import TruckCreate from './components/TruckCreate';
import DeliveryManagerHome from './components/DeliveryManagerHome';
import DriversView from './components/DriversView';
import ViewOneDriver from './components/OneDriverView';
import TrucksView from './components/TrucksView';
import OneTruckView from './components/OneTruckView';
import ViewAllOrders from './components/OrdersView';
import ViewOneOrder from './components/OneOrderView';
import ViewEngagements from './components/EngagementsView';
import SupplyManagerHome from './components/SupplyManagerHome';
import Contracts from './components/Contracts';
import DeleteContract from './components/DeleteContract';
import UpdateContract from './components/UpdateContract';
import AddNewContract from './components/AddNewContract';

import Companies from './components/Companies';


import MachinesView from "./components/MachinesView";
import ProductionManagerHome from "./components/ProductionManagerHome";
import OneMachineView from "./components/OneMachineView";
import IngredientsView from "./components/IngredientsView";
import OneIngredientView from "./components/OneIngredientView";
import AddMachine from "./components/AddMachine";
import AddIngredient from "./components/AddIngredient";
import ContractsDetails from './components/ContractDetails';

import ProductionRequests from "./components/ProductionRequests";
import ProductsView from "./components/ProductsView";
import Recipe from "./components/Recipe"
import ViewPlanForTomorrow from './components/ViewPlanForTomorrow';
import ViewPlanForToday from './components/ViewPlanForToday';
import DeliveredOrdersView from './components/DeliveredOrdersView';

import OneProductionRequestReview from "./components/OneProductionRequestReview";
import EngagementCreate from './components/EngagementCreate';
import InProductionOrders from "./components/InProductionOrders";
import IngredientRequests from "./components/IngredientRequests";
import ViewPlanForTodayForDriver from './components/DriverHomePage';
import ViewPlanForTomorrowForDriver from './components/DriverTomorrowPlan';
import BasicDocument from './components/OrderPDF';
import DriverPDF from './components/DriverPDF';
import ToursReport from './components/ToursReport';
import FinishedOrders from "./components/FinishedOrders";
import OrdersReport from "./components/OrdersReport";
// const root = ReactDOM.createRoot(document.getElementById('root'));
// root.render(
//   <React.StrictMode>
//     <App />
//   </React.StrictMode>
// );

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
// reportWebVitals();
ReactDOM.render(
  <Router>
    <Routes>
        <Route path='/' element={<Login/>}/>
        <Route path='/userRegistration' element={<UserRegistration/>}/>
        <Route path='/regularUserHomepage' element = {<RegularUserHomepage/>}/>
        <Route path='/systemAdminHomepage' element = {<ManagerRegistration/>}/>
        <Route path= '/UserProfileUpdate'  element = {<UserProfileUpdate/>}/>
        <Route path='/DriverCreate' element = {<DriverCreate/>}/>
        <Route path='/TruckCreate' element = {<TruckCreate/>}/>
        <Route path='/DeliveryManagerHome' element = {<DeliveryManagerHome/>}/>
        <Route path='/ProductionManagerHome' element = {<ProductionManagerHome/>}/>
        <Route path='/DriversView' element = {<DriversView/>}/>
        <Route path='/OneDriverView' element = {<ViewOneDriver/>}/>
        <Route path='/TrucksView' element = {<TrucksView/>}/>
        <Route path='/OneTruckView' element = {<OneTruckView/>}/>
        <Route path='/OrdersView' element = {<ViewAllOrders/>}/>
        <Route path='/OneOrderView' element = {<ViewOneOrder/>}/>
        <Route path='/EngagementsView' element = {<ViewEngagements/>}/>
        <Route path='/SupplyManagerHome' element = {<SupplyManagerHome/>}/>
        <Route path='/contracts' element = {<Contracts/>}/>
        <Route path='/deleteContract' element = {<DeleteContract/>}/>
        <Route path='/updateContract' element = {<UpdateContract/>}/>
        <Route path='/addNewContract' element = {<AddNewContract/>}/>

        <Route path='/companies' element = {<Companies/>}/>
        

        <Route path='/MachinesView' element = {<MachinesView/>}/>
        <Route path='/OneMachineView' element = {<OneMachineView/>}/>
        <Route path='/IngredientsView' element = {<IngredientsView/>}/>
        <Route path='/OneIngredientView' element = {<OneIngredientView/>}/>
        <Route path='/AddMachine' element = {<AddMachine/>}/>
        <Route path='/AddIngredient' element = {<AddIngredient/>}/>
        <Route path='/contractsDetails' element = {<ContractsDetails/>}/>
        <Route path='/ProductionRequests' element = {<ProductionRequests/>}/>
        <Route path='/ProductsView' element = {<ProductsView/>}/>
        <Route path='/Recipe' element = {<Recipe/>}/>
        <Route path='/ViewPlanForTomorrow' element = {<ViewPlanForTomorrow/>}/>
        <Route path='/ViewPlanForToday' element = {<ViewPlanForToday/>}></Route>
        <Route path='/DeliveredOrdersView' element = {<DeliveredOrdersView/>}/>
        <Route path='/OneProductionRequestReview' element = {<OneProductionRequestReview/>}/>
        <Route path='/EngagementCreate' element = {<EngagementCreate/>}/>
        <Route path='/DriverHomePage' element = {<ViewPlanForTodayForDriver/>}/>
        <Route path='/DriverTomorrowPlan' element = {<ViewPlanForTomorrowForDriver/>}/>
        <Route path='/InProductionOrders' element = {<InProductionOrders/>}/>
        <Route path='/IngredientRequests' element = {<IngredientRequests/>}/>
        <Route path='/OrderPdf' element = {<BasicDocument/>}/>
        <Route path='/DriverPDF' element = {<DriverPDF/>}/>
        <Route path='/ToursReport' element = {<ToursReport/>}/>
        <Route path='/OrdersReport' element = {<OrdersReport/>}/>
        <Route path='/FinishedOrders' element = {<FinishedOrders/>}/>
    </Routes>
  </Router>,
 document.getElementById('root')
);