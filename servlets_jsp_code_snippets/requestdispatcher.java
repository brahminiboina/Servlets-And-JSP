public class PaytmTransactionReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public PaytmTransactionReportServlet() 
    {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException 
	{
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		service(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		service(request,response);	
	}
	
	private static Logger log = Logger.getLogger("PaytmTransactionReportServlet");
	
	protected void service(HttpServletRequest request, HttpServletResponse response) {
		try 
		{
			Jsonresp1 = PaytmTransactionReportService.getTransactionStatus(paytmBean, fromDate, toDate );
			json_response.put("jsonObject1", Jsonresp1);
			
			Jsonresp2 = PaytmTransactionReportDao.getTransactionDetails(paytmBean, fromDate, toDate );
			json_response.put("jsonObject2", Jsonresp2);
			
			log.info("JSONRESPONSE IS:   " +json_response);
			
			HttpSession session=request.getSession(true);
			session.setAttribute("jsonObject", json_response);
			RequestDispatcher rd = request.getRequestDispatcher("/admin/reports/paytm_transaction_report_db.jsp");
			rd.forward(request, response);
	       
		}catch (Exception e)
        {
			e.printStackTrace();
		}
	}
}