package service.AAAHDISINIESTRO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import com.avaya.collaboration.util.logger.Logger;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

/**
 * 
 * Servlet implementation class HelloServlet
 * 
 */
@WebServlet("/autoPronto")
public class autoPronto extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
//	private final Logger logger;    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public autoPronto() 
    {
        super();
//        logger = Logger.getLogger(getClass());
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * Method GET elements and Get elements by id ?id=1001
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{	
//		logger.info("Método GET");
		PrintWriter out = response.getWriter();
		conexion conn = new conexion();
		try {
			Connection conexion = conn.conn();
//			logger.info("Conexión a Postgres Exitosa");
			
			/*SOLO EVALUA numsiniestro*/
			if(request.getParameter("numsiniestro") != null){
				String numeroSiniestro_key = request.getParameter("numsiniestro");
				long numeroSiniestro = Long.parseLong(numeroSiniestro_key);
//				logger.info("Parametro: "+numeroSiniestro_key);
				String SQL = "SELECT * FROM siniestrohdi WHERE numsiniestro = "+numeroSiniestro+";";
				try (Statement stmt = conexion.createStatement();
		                ResultSet rs = stmt.executeQuery(SQL)) {
		            List<autoProntoBean> list = new LinkedList<>();
		            while (rs.next()) {
		            	autoProntoBean user = new autoProntoBean();
	                	user.setId(rs.getInt("id"));
	                	user.setNumContrato(rs.getInt("numcontrato"));
	                	user.setNumSiniestro(rs.getLong("numsiniestro"));
	                	user.setNombre(rs.getString("nombre"));
	                	user.setFechaIngreso(rs.getString("fechaingreso"));
	                	user.setFechaSalida(rs.getString("fechasalida"));
	                	user.setStatus(rs.getString("status"));
	                	user.setMobile(rs.getString("mobile"));
	                	user.setLinkRazon(rs.getString("linkrazon"));
	                	user.setLinkDetalleCliente(rs.getString("linkdetallecliente"));
	                	list.add(user);
		            }
		            Gson jsonGson = new GsonBuilder().setPrettyPrinting().create();
		            out.println(jsonGson.toJson(list));
		            response.setContentType("application/json");
		            response.setCharacterEncoding("UTF-8");
		            response.setStatus(200);
		            conexion.close();
		            stmt.close();
		            rs.close();
		        } catch (SQLException e) {
//		        	logger.info("autoPronto GET?NumSiniestro = Error: "+e.getMessage());
		        	/*RESPUESSTA ERROR EN FORMATO JSON*/
		            JsonObject error = new JsonObject();
//		            response.setContentType("application/json");
//		            response.setCharacterEncoding("UTF-8");
//		            response.setStatus(200);
		            error.addProperty("Status", "Error");
		            error.addProperty("Error", e.getMessage());
		            out.println(error);
		            response.setStatus(404);
		            response.setContentType("application/json");
		            response.setCharacterEncoding("UTF-8");
		        }
			}
			
			/*SOLO EVALUA mobile*/
			if(request.getParameter("mobile") != null){
				String mobile_key = request.getParameter("mobile");
//				int Mobile = Integer.parseInt(mobile_key);
//				logger.info("Parametro: "+mobile_key);
				String SQL = "SELECT * FROM siniestrohdi WHERE mobile = "+mobile_key+";";
				try (Statement stmt = conexion.createStatement();
		                ResultSet rs = stmt.executeQuery(SQL)) {
		            List<autoProntoBean> list = new LinkedList<>();
		            while (rs.next()) {
		            	autoProntoBean user = new autoProntoBean();
	                	user.setId(rs.getInt("id"));
	                	user.setNumContrato(rs.getInt("numcontrato"));
	                	user.setNumSiniestro(rs.getLong("numsiniestro"));
	                	user.setNombre(rs.getString("nombre"));
	                	user.setFechaIngreso(rs.getString("fechaingreso"));
	                	user.setFechaSalida(rs.getString("fechasalida"));
	                	user.setStatus(rs.getString("status"));
	                	user.setMobile(rs.getString("mobile"));
	                	user.setLinkRazon(rs.getString("linkrazon"));
	                	user.setLinkDetalleCliente(rs.getString("linkdetallecliente"));
	                	list.add(user);
		            }
		            Gson jsonGson = new GsonBuilder().setPrettyPrinting().create();
		            out.println(jsonGson.toJson(list));
		            response.setContentType("application/json");
		            response.setCharacterEncoding("UTF-8");
		            response.setStatus(200);
		            conexion.close();
		            stmt.close();
		            rs.close();
		        } catch (SQLException e) {
//		        	logger.info("autoPronto GET?NumSiniestro = Error: "+e.getMessage());
		        	/*RESPUESSTA ERROR EN FORMATO JSON*/
		            JsonObject error = new JsonObject();
		            response.setContentType("application/json");
		            response.setCharacterEncoding("UTF-8");
		            response.setStatus(200);
		            error.addProperty("Status", "Error");
		            error.addProperty("Error", e.getMessage());
		            out.println(error);
		            response.setStatus(404);
		            response.setContentType("application/json");
		            response.setCharacterEncoding("UTF-8");
		        }
			}
			
			
			/*EVALÚA SI SE ENVÍA UN PARAMETRO EN ESTE CASO NO SE ENVÏA PARÁMETRO*/
			if(request.getParameter("numsiniestro") == null && request.getParameter("mobile") == null){
				String SQL = "SELECT * FROM siniestrohdi ORDER BY numsiniestro;";
				Statement stmt = conexion.createStatement();
                ResultSet rs = stmt.executeQuery(SQL);
                List<autoProntoBean> list = new LinkedList<>();
                while (rs.next()) {
                	autoProntoBean user = new autoProntoBean();
                	user.setId(rs.getInt("id"));
                	user.setNumContrato(rs.getInt("numcontrato"));
                	user.setNumSiniestro(rs.getLong("numsiniestro"));
                	user.setNombre(rs.getString("nombre"));
                	user.setFechaIngreso(rs.getString("fechaingreso"));
                	user.setFechaSalida(rs.getString("fechasalida"));
                	user.setStatus(rs.getString("status"));
                	user.setMobile(rs.getString("mobile"));
                	user.setLinkRazon(rs.getString("linkrazon"));
                	user.setLinkDetalleCliente(rs.getString("linkdetallecliente"));
                	list.add(user);
                }
                /*RESPUESTA EN JSON*/
	            Gson jsonGson = new GsonBuilder().setPrettyPrinting().create();
	            response.setContentType("application/json");
	            response.setCharacterEncoding("UTF-8");
	            response.setStatus(200);
	            out.println(jsonGson.toJson(list));
	            conexion.close();
	            stmt.close();
	            rs.close();
                
			}

		} catch (Exception e) {
//			logger.info("autoPronto GET Error: "+ e.getMessage());
			/*RESPUESSTA ERROR EN FORMATO JSON*/
            JsonObject error = new JsonObject();
            error.addProperty("Status", "Error");
            error.addProperty("Error", e.getMessage());
            out.println(error);
            response.setStatus(404);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
			e.printStackTrace();
		} 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
//		logger.info("Método POST");
		PrintWriter out = response.getWriter();
		conexion conn = new conexion();
		long id = 0;
		try{
			Connection conexion = conn.conn();
//			logger.info("Realizamos la conexión");
			conn.conn();
//			logger.info("Conexión a Postgres Exitosa");
			BufferedReader reader = request.getReader();
	        Gson gson = new Gson();
	        autoProntoBean myBean = gson.fromJson(reader, autoProntoBean.class);
	        Statement stmt = conexion.createStatement();
	        int affectedRows = stmt.executeUpdate("insert into siniestrohdi (id, numcontrato , numsiniestro , nombre , fechaingreso , fechasalida ,status , mobile , linkrazon , linkdetallecliente) values "+
	        														   "(" + myBean.getId() + ", " 
	        														   + myBean.getNumContrato() 
	        														   + " ," + myBean.getNumSiniestro() 
	        														   + " ,'" + myBean.getNombre() 
	        														   + "','" + myBean.getFechaIngreso() 
	        														   + "', '" + myBean.getFechaSalida() 
	        														   + "', '"+myBean.getStatus()
	        														   + "', '"+myBean.getMobile()
	        														   + "', '"+myBean.getLinkRazon()
	        														   + "', '"+myBean.getLinkDetalleCliente()+"')");
	        /*EVALUA SI SE REALIZÓ EL INSERT*/
	        if (affectedRows > 0) {
	        	ResultSet rs = stmt.getGeneratedKeys(); 
                    if (rs.next()) {
                        id = rs.getLong(1);
//                        logger.info("Valor de id = rs.getLong(1); = "+id);
                    }
//                logger.info("Numero de columnas insertadas: "+affectedRows);
                /*RESPUESSTA OK EN FORMATO JSON*/
                JsonObject ok = new JsonObject();
                ok.addProperty("Status", "Ok");
                out.println(ok);
                response.setStatus(200);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
            }

	        conexion.close();
	        
		}catch(Exception e){
//			logger.info("Error: "+e.getMessage());
			/*RESPUESSTA ERROR EN FORMATO JSON*/
            JsonObject error = new JsonObject();
            error.addProperty("Status", "Error");
            error.addProperty("Error", e.getMessage());
            out.println(error);
            response.setStatus(403);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
			e.printStackTrace();
		}
		
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
//		logger.info("Método PUT");
		PrintWriter out = response.getWriter();
		conexion conn = new conexion();
		try{
			Connection conexion = conn.conn();
//			logger.info("Realizamos la conexión");
			conn.conn();
//			logger.info("Conexión a Postgres Exitosa");
			BufferedReader reader = request.getReader();
	        Gson gson = new Gson();
	        autoProntoBean myBean = gson.fromJson(reader, autoProntoBean.class);
	        Statement stmt = conexion.createStatement();
	        int affectedRows = stmt.executeUpdate("UPDATE siniestrohdi SET id ='" + myBean.getId()
                    + "', numcontrato ='" + myBean.getNumContrato()
                    + "', numsiniestro ='" + myBean.getNumSiniestro()
                    + "', nombre ='" + myBean.getNombre()
                    + "', fechaingreso ='" + myBean.getFechaIngreso()
                    + "', fechasalida ='" + myBean.getFechaSalida()
                    + "', status ='" + myBean.getStatus()
                    + "', mobile ='" + myBean.getMobile()
                     + "',linkrazon ='" + myBean.getLinkRazon()
                    + "', linkdetallecliente ='" + myBean.getLinkDetalleCliente()
                    + "' WHERE numsiniestro = " + myBean.getNumSiniestro() + ";");
	        /*EVALUA SI SE REALIZÓ EL UPDATE*/
	        if (affectedRows > 0) {
//                logger.info("Numero de columnas insertadas: "+affectedRows);
                /*RESPUESSTA OK EN FORMATO JSON*/
                JsonObject ok = new JsonObject();
                ok.addProperty("Status", "Ok");
                out.println(ok);
                response.setStatus(200);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
            }
            if (affectedRows == 0){
//            	logger.info("Numero de columnas insertadas: "+affectedRows);
            	JsonObject error = new JsonObject();
                error.addProperty("Status", "Error");
                error.addProperty("Error", "No se ha realizado ningún cambio / No existe numsiniestro "+myBean.getNumSiniestro()+"");
                out.println(error);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
            }
            conexion.close();
        } catch (Exception ex) {
//        	logger.info("autoPronto Método PUT con error: "+ex.getMessage());
        	/*RESPUESSTA ERROR EN FORMATO JSON*/
            JsonObject error = new JsonObject();
            error.addProperty("Status", "Error");
            error.addProperty("Error", ex.getMessage());
            out.println(error);
            response.setStatus(409);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

        }
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
//		logger.info("Método DELETE");
		PrintWriter out = response.getWriter();
		conexion conn = new conexion();
		try{
			Connection conexion = conn.conn();
//			logger.info("Realizamos la conexión");
			conn.conn();
//			logger.info("Conexión a Postgres Exitosa");
			BufferedReader reader = request.getReader();
	        Gson gson = new Gson();
	        autoProntoBean myBean = gson.fromJson(reader, autoProntoBean.class);
	        Statement stmt = conexion.createStatement();
	        /*ASIGNANDO VALORES INPUT A VARIABLES LOCALES*/
            long numsiniestro = myBean.getNumSiniestro();
            /*STATEMENT*/
            int affectedRows = stmt.executeUpdate("DELETE FROM siniestrohdi \n" +
                                                        "	WHERE numsiniestro = "+numsiniestro+";");
            /*EVALUA SI SE REALIZÓ EL DELETE*/
            if (affectedRows > 0) {
                // get the ID back
//                logger.info("Numero de columnas insertadas: "+affectedRows);
                /*RESPUESSTA OK EN FORMATO JSON*/
                JsonObject ok = new JsonObject();
                ok.addProperty("Status", "Ok");
                out.println(ok);
                response.setStatus(200);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
            }
            if (affectedRows == 0){
//            	logger.info("Numero de columnas insertadas: "+affectedRows);
            	JsonObject error = new JsonObject();
                error.addProperty("Status", "Error");
                error.addProperty("Error", "No se ha realizado ningún cambio / No existe numsiniestro "+myBean.getNumSiniestro()+"");
                out.println(error);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
            }
            conexion.close();
        } catch (Exception ex) {
//        	logger.info("autoPronto Método PUT con error: "+ex.getMessage());
        	/*RESPUESSTA ERROR EN FORMATO JSON*/
            JsonObject error = new JsonObject();
            error.addProperty("Status", "Error");
            error.addProperty("Error", ex.getMessage());
            out.println(error);
            response.setStatus(409);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

        }
	}
	
}