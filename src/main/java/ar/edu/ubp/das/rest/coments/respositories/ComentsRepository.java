package ar.edu.ubp.das.rest.coments.respositories;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import ar.edu.ubp.das.rest.coments.beans.SugerenciaBean;
import ar.edu.ubp.das.rest.coments.beans.TemasTipoServicioBean;
import ar.edu.ubp.das.rest.coments.beans.TipoServicioBean;


@Repository
public class ComentsRepository {

	@Autowired
	 private JdbcTemplate jdbcTpl;
	
	@SuppressWarnings("unchecked")
	public List<TemasTipoServicioBean>getTemasTipoServicio(String cod){
		
		 SqlParameterSource in = new MapSqlParameterSource()
		           .addValue("cod_tipo_servicio", cod);
		 
		        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTpl)
		    	   .withProcedureName("get_temas_tipo_servicio")
		           .withSchemaName("dbo")
		       	   .returningResultSet("temas_tipo_servicio", BeanPropertyRowMapper.newInstance(TemasTipoServicioBean.class));
		       	
		       	Map<String, Object> out = jdbcCall.execute(in);
		       	
		       	return (List<TemasTipoServicioBean>)out.get("temas_tipo_servicio");
	}
	
	@SuppressWarnings("unchecked")
	public void insertSugerencia(SugerenciaBean sug){
		
		 SqlParameterSource in = new MapSqlParameterSource()
				 .addValue("cod_tipo_servicio", sug.getCodTipoServicio())
				 .addValue("nro_tema", sug.getNroTema())
				 .addValue("sugerencia", sug.getSugerencia());
		
		 SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTpl)
		           .withProcedureName("ins_sugerencia")
		           .withSchemaName("dbo");
		 
		 Map<String, Object> out = jdbcCall.execute(in);
		       	
		 return;
	}
	
	@SuppressWarnings("unchecked")
	public List<TipoServicioBean>getTiposServicios(){
		
		 SqlParameterSource in = new MapSqlParameterSource();
		 
		        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTpl)
		    	   .withProcedureName("get_tipos_servicios")
		           .withSchemaName("dbo")
		       	   .returningResultSet("tipos_servicios", BeanPropertyRowMapper.newInstance(TipoServicioBean.class));
		       	
		       	Map<String, Object> out = jdbcCall.execute(in);
		       	
		       	return (List<TipoServicioBean>)out.get("tipos_servicios");
	}
	
	
	
	
	
}
