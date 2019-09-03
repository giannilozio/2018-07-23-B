package it.polito.tdp.newufosightings.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.newufosightings.model.Sighting;
import it.polito.tdp.newufosightings.model.State;

public class NewUfoSightingsDAO {

	public List<Sighting> loadAllSightings() {
		String sql = "SELECT * FROM sighting";
		List<Sighting> list = new ArrayList<>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				list.add(new Sighting(res.getInt("id"), res.getTimestamp("datetime").toLocalDateTime(),
						res.getString("city"), res.getString("state"), res.getString("country"), res.getString("shape"),
						res.getInt("duration"), res.getString("duration_hm"), res.getString("comments"),
						res.getDate("date_posted").toLocalDate(), res.getDouble("latitude"),
						res.getDouble("longitude")));
			}

			conn.close();
			return list;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
	}

	public List<State> loadAllStates(Map<String, State> idMap) {
		String sql = "SELECT * FROM state";
		List<State> result = new ArrayList<State>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				State state = new State(rs.getString("id"), rs.getString("Name"), rs.getString("Capital"),
						rs.getDouble("Lat"), rs.getDouble("Lng"), rs.getInt("Area"), rs.getInt("Population"),
						rs.getString("Neighbors"));
				idMap.put(rs.getString("id"), state);
				result.add(state);
			}

			conn.close();
			return result;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
	}

	public List<ViciniPesati> getAllPesi(int anno, int giorni, Map<String, State> idMap) { 			// PRENDO I 2 VICINI DA NEIGHBOR E GUARDO SE HANNO AVUTO UN EVENTO ENTRO QUEL PERIODO
		
		String sql = "SELECT n.state1,n.state2,COUNT(s1.id + s2.id) AS peso "+
					 "FROM sighting as s1, sighting as s2 ,neighbor AS n "+
					 "WHERE n.state1=s1.state "+
					 "AND n.state2=s2.state "+
					 "AND YEAR(s1.datetime) = ? "+
					 "AND YEAR(s1.datetime) = YEAR(s2.DATETIME) "+
					 "AND ((DATEDIFF(s1.datetime,s2.DATETIME) <= ? AND DATEDIFF(s1.datetime,s2.datetime) > 0 ) "+ 
					 "OR (DATEDIFF(s2.datetime,s1.DATETIME) <= ? AND DATEDIFF(s2.datetime,s1.datetime) > 0)) "+
					 "GROUP BY n.state1,n.state2";
		
		List<ViciniPesati> result = new ArrayList<>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, anno);
			st.setInt(2,giorni);
			st.setInt(3, giorni);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				
				State vp1 = idMap.get(rs.getString("n.state1"));
				State vp2 = idMap.get(rs.getString("n.state2"));
				int peso = rs.getInt("peso");
				result.add(new ViciniPesati(vp1,vp2,peso));
			}

			conn.close();
			return result;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
		
	}

}
