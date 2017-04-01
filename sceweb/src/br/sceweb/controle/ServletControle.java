package br.sceweb.controle;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import br.sceweb.modelo.Empresa;
import br.sceweb.modelo.EmpresaDAO;

/**
 * Servlet implementation class ServletControle
 * @author Lab103
 * @version $Revision: 1.0 $
 */
public class ServletControle extends HttpServlet {
	/**
	 * Field serialVersionUID.
	 * (value is 1)
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Field logger.
	 */
	Logger logger = Logger.getLogger(ServletControle.class);
	/**
	 * Field mensagem.
	 */
	String mensagem = "";
	/**
	 * Field empresaDAO.
	 */
	EmpresaDAO empresaDAO;
	/**
	 * Field cnpjParaExclusao.
	 */
	String cnpjParaExclusao = "";// seta o cnpj para exclusao

	/**
	 * Default constructor.
	 */
	public ServletControle() {
		// TODO Auto-generated constructor stub

	}

	/**
	
	 * @param request HttpServletRequest isso é uma variavel de requisicao
	 * @param response HttpServletResponse
	 * @throws ServletException
	 * @throws IOException
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response) */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	
	/**
	 * Method doPost.
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("erro", null);
		executaComando(request, response);
	}

	/**
	 * Method executaComando.
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void executaComando(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String parametro = request.getParameter("acao");
		logger.info("acao do servletcontrole = " + parametro);
		String url = "";
		String resultado = "";
		request.setAttribute("erro", null);
		if (parametro.equals("IncluirEmpresa")) {
			url = "/visao/FormEmpresa.jsp";
			try {
				resultado = cadastrarEmpresa(request.getParameter("txtCNPJ"),
						request.getParameter("txtNomeDaEmpresa"),
						request.getParameter("txtNomeFantasia"),
						request.getParameter("txtEndereco"),
						request.getParameter("txtTelefone"));
				logger.info("resultado do cadastra = " + resultado);
				request.setAttribute("msg", resultado);
				request.getRequestDispatcher(url).forward(request, response);
			} catch (Exception e) {
				request.setAttribute("msg", resultado);
				request.getRequestDispatcher(url).forward(request, response);
				logger.info("erro  = " + e.getMessage());

			}
		}
		if (parametro.equals("ConsultarEmpresa")) {
			url = "/visao/FormEmpresa.jsp";
			Empresa empresa = new Empresa();
			String cnpj = request.getParameter("txtCNPJ");
			logger.info("consulta empresa  = " + cnpj);
			try {
				if (!cnpj.isEmpty()) {
					empresa = consulta(cnpj);
					if (empresa != null) {
						logger.info("consulta empresa nome da empresa  = "
								+ empresa.getNomeDaEmpresa());
						request.setAttribute("nomeDaEmpresa",
								empresa.getNomeDaEmpresa());
						request.setAttribute("cnpj", empresa.getCnpj());
						request.setAttribute("nomeFantasia",
								empresa.getNomeFantasia());
						request.setAttribute("endereco", empresa.getEndereco());
						request.setAttribute("telefone", empresa.getTelefone());
						request.setAttribute("msg", "");
						url = "/visao/FormEmpresaResultadoDaConsulta.jsp";
					} else {
						request.setAttribute("msg", "cnpj invalido");
					}
				} else {
					request.setAttribute("msg", "cnpj invalido");
				}
			} catch (Exception e) {
				logger.info(e.getMessage() + e.getCause());
			}
			request.getRequestDispatcher(url).forward(request, response);

		}

	}

	/**
	 * Method cadastrarEmpresa.
	 * @param cnpj String
	 * @param nomeDaEmpresa String
	 * @param nomeFantasia String
	 * @param endereco String
	 * @param telefone String
	 * @return String
	 */
	public String cadastrarEmpresa(String cnpj, String nomeDaEmpresa,
			String nomeFantasia, String endereco, String telefone) {
		String msg = "";
		Empresa empresa = new Empresa();
		EmpresaDAO empresaDAO = new EmpresaDAO();
		try {
			empresa.setCnpj(cnpj);
			empresa.setNomeDaEmpresa(nomeDaEmpresa);
			empresa.setNomeFantasia(nomeFantasia);
			empresa.setEndereco(endereco);
			empresa.setTelefone(telefone);
			empresaDAO.adiciona(empresa);
			msg = "cadastro realizado com sucesso";

		} catch (Exception e) {
			msg = e.getMessage();
		}

		return msg;
	}

	/**
	 * Method consulta.
	 * @param cnpj String
	 * @return Empresa
	 */
	public Empresa consulta(String cnpj) {
		logger.info("consulta empresa 2 = " + cnpj);
		EmpresaDAO empresaDAO = new EmpresaDAO();
		return empresaDAO.consultaEmpresa(cnpj);
	}

	/**
	 * Method excluirEmpresa.
	 * @param cnpj String
	 * @return String
	 */
	public String excluirEmpresa(String cnpj) {
		String msg = "";
		EmpresaDAO empresaDAO = new EmpresaDAO();
		try {
			empresaDAO.exclui(cnpj);
			msg = "excluido com sucesso";
		} catch (Exception e) {
			msg = e.getMessage();
		}

		return msg;
	}
}