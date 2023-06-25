import './styles/AddNewForm.css'

function Home(){

    return(
        <body>
            <div class="topnav">
                <a class="active" href="/DeliveryManagerHome">Home</a>
                <a href="/DriversView">Drivers Review</a>
                <a href="/TrucksView">Trucks Review</a>
                <a href="/OrdersView">Completed Orders Review</a>
                <a href="/DeliveredOrdersView">Delivered Orders Review</a>
                <a href="/EngagementsView">Engagements Review</a>
                <a href='/ViewPlanForTomorrow'>Plan For Tomorrow Review</a>
                <a href="/ViewPlanForToday">Plan For Today Review</a>
                <a href="/">Logout</a>
            </div>
        </body>
    )
}

export default Home;