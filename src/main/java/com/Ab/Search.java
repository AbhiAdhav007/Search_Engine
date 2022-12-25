package com.Ab;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

//Getting the data from user and send to the server
@WebServlet("/Search")
public class Search extends HttpServlet {

    protected void doGet(HttpServletRequest request , HttpServletResponse response){
        //Get the keyword from text field or search field
        String keyword = request.getParameter("keyword");
        System.out.println(keyword);
        try{
            Connection connection = DataBaseConnection.getConnection();
            //add keyword into History table
            PreparedStatement preparedStatement = connection.prepareStatement("Insert into history values(? , ?)");
            preparedStatement.setString(1 , keyword);
            preparedStatement.setString(2 , "http://localhost:8080/SearchEngine/Search?keyword="+keyword);
            preparedStatement.executeUpdate();

            //get results from the data base by page table
            ResultSet resultSet = connection.createStatement().executeQuery("select pageTitle ,pageLink, (length(lower(pageData))-length(replace(lower(pageData), '"+keyword+"' ,\"\")))/length('"+keyword+"') as countoccurance from pages order by countoccurance desc;");
            //select pageTitle ,pageLink, (length(lower(pageData))-length(replace(lower(pageData), "java","")))/length("java") as countoccurance from pages order by countoccurance desc;
            ArrayList<SearchResult> results = new ArrayList<SearchResult>();
            while(resultSet.next()){
                SearchResult searchresult = new SearchResult();
                searchresult.setPageTitle(resultSet.getString("pageTitle"));
                searchresult.setPageLink(resultSet.getString("pageLink"));
                results.add(searchresult);
            }
            for(SearchResult result :results){
                System.out.println(result.getPageLink() + " " + result.getPageTitle() + "\n");

            }
            request.setAttribute("results" , results);
            request.getRequestDispatcher("/search.jsp").forward(request, response );
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
        }
        catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        catch (ServletException servletException){
            servletException.printStackTrace();
        }
        catch(IOException ioException){
            ioException.printStackTrace();
        }
    }
}
