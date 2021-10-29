package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SellerDaoJDBC implements SellerDao {

    private Connection conn;

    public SellerDaoJDBC(Connection conn){
        this.conn = conn;
    }

    @Override
    public void insert(Seller obj) {

    }

    @Override
    public void update(Seller obj) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Seller findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT seller.*,department.Name as DepName"
                           + " FROM seller INNER JOIN department"
                           + " ON seller.DepartmentId = department.Id"
                           + " WHERE seller.Id = ?"
            );

            st.setInt(1, id);
            rs = st.executeQuery(); //recebe a tabela

            if (rs.next()) { //verifica se foi recebido algum valor, caso sim ele executa o if

                Department dep = instanciateDepartment(rs);

                Seller sell = instanciateSeller(rs, dep);

                return sell;
            }
            return null;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }

        finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
            //não fecha a conexão pois o mesmo objeto pode servir para mais de uma operação
        }


    }

    private Seller instanciateSeller(ResultSet rs, Department dep) throws SQLException{
        Seller sell = new Seller();
        sell.setId(rs.getInt("Id"));
        sell.setName(rs.getString("Name"));
        sell.setEmail(rs.getString("Email"));
        sell.setBaseSalary(rs.getDouble("BaseSalary"));
        sell.setBirthDate(rs.getDate("birthDate"));
        sell.setDepartment(dep);
        return sell;
    }

    private Department instanciateDepartment(ResultSet rs) throws SQLException{

        Department dep = new Department(); //instancia um novo objeto department
        dep.setId(rs.getInt("DepartmentId")); //passa o id que ta no banco para o id do objeto
        dep.setName(rs.getString("DepName")); //passa o nome que ta no banco para o nome no objeto
        return dep;
    }

    @Override
    public List<Seller> findAll() {
        return null;
    }
}
