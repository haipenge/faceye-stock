package com.faceye.component.stock.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.faceye.component.stock.entity.AccountingElement;
import com.faceye.component.stock.entity.AccountingSubject;
import com.faceye.component.stock.entity.ReportCategory;
import com.faceye.component.stock.service.AccountingElementService;
import com.faceye.component.stock.service.AccountingSubjectService;
import com.faceye.component.stock.service.GenerateFinancialStructService;
import com.faceye.component.stock.service.ReportCategoryService;
import com.faceye.feature.util.FileUtil;
import com.google.common.base.CaseFormat;

/**
 * 生成财报基础数据操作对像
 * 
 * @author haipenge
 *
 */
@Service
public class GenerateFinancialStructServiceImpl implements GenerateFinancialStructService {
//	private String path = "/work/project/faceye-stock/faceye-stock-manager/target/generated-sources";
	private String path = "/work/project/faceye-stock/faceye-stock-entity/src/main/java/com/faceye/component/stock/entity";
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private ReportCategoryService reportCategoryService = null;
	@Autowired
	private AccountingElementService accountingElementService = null;
	@Autowired
	private AccountingSubjectService accountingSubjectService = null;

	@Override
	public void generate() {
		this.write();
	}

	private void write() {
		List<ReportCategory> reportCategories = this.reportCategoryService.getAll();
		// 财务报表
		StringBuffer report = new StringBuffer();
		report.append("/**\n*财报\n*/\n");
		report.append("package com.faceye.component.stock.entity;\n");
		report.append("@Document(collection=\"stock_report_data\")\n");
		report.append("public class ReportData{\n");
		for (ReportCategory reportCategory : reportCategories) {
			report.append("/**\n");
			report.append("*");
			report.append(reportCategory.getName() + "(" + reportCategory.getId() + "\n");
			report.append("*");
			report.append(reportCategory.getCode() + "\n");
			report.append("*/\n");
			report.append("private " + reportCategory.getCode() + " " + reportCategory.getCode().toLowerCase() + " = new "+reportCategory.getCode()+"();\n");
			report.append("public void set" + reportCategory.getCode() + "(" + reportCategory.getCode() + " " + reportCategory.getCode().toLowerCase() + "){\n");
			report.append(" this." + reportCategory.getCode().toLowerCase() + " = " + reportCategory.getCode().toLowerCase() + ";\n");
			report.append("}\n");
			report.append("public " + reportCategory.getCode() + " get" + reportCategory.getCode() + "(){\n");
			report.append(" return this." + reportCategory.getCode().toLowerCase() + ";\n");
			report.append("}\n");

			StringBuffer cReport = new StringBuffer();// 对应资产负债表,利润表等
			cReport.append("package com.faceye.component.stock.entity;\n");
			cReport.append("/**\n");
			cReport.append("*" + reportCategory.getName() + "(" + reportCategory.getId() + ")\n");
			cReport.append("*/\n");
			cReport.append("public class " + reportCategory.getCode() + "{\n");

			Map params = new HashMap();
			params.put("EQ|reportCategory.$id", reportCategory.getId());
			List<AccountingElement> accountingElements = this.accountingElementService.getPage(params, 0, 0).getContent();
			for (AccountingElement accountingElement : accountingElements) {
				cReport.append("/**\n");
				cReport.append("*" + accountingElement.getName() + " (" + accountingElement.getId() + ")" + "\n");
				cReport.append("*/\n");
				cReport.append("private Ele" + accountingElement.getId() + " ele" + accountingElement.getId() + " = new Ele"+accountingElement.getId()+"();\n");
				cReport.append("public void setEle" + accountingElement.getId() + "(Ele" + accountingElement.getId() + " ele" + accountingElement.getId() + "){\n");
				cReport.append("this.ele" + accountingElement.getId() + " = " + " ele" + accountingElement.getId() + ";\n");
				cReport.append("}\n");
				cReport.append("public Ele" + accountingElement.getId() + " getEle" + accountingElement.getId() + "(){\n");
				cReport.append("return this.ele" + accountingElement.getId() + ";\n");
				cReport.append("}\n");

				StringBuffer eReport = new StringBuffer();// 财务报表 中的各个条目
				eReport.append("package com.faceye.component.stock.entity;\n");
				eReport.append("/**\n");
				eReport.append("*" + accountingElement.getName() + " (" + accountingElement.getId() + " )\n");
				eReport.append("*所属报表:" + reportCategory.getName() + "->" + accountingElement.getName() + "\n");
				eReport.append("*/\n");
				eReport.append("public class Ele" + accountingElement.getId() + "{\n");
				params = new HashMap();
				params.put("EQ|accountingElement.$id", accountingElement.getId());
				List<AccountingSubject> accountingSubjects = this.accountingSubjectService.getPage(params, 0, 0).getContent();
				for (AccountingSubject accountingSubject : accountingSubjects) {
					eReport.append("/**\n");
					eReport.append("*" + accountingSubject.getName() + " : " + accountingSubject.getCode() + " : " + accountingSubject.getId() + ";\n");
					eReport.append("*Sina URL is:" + accountingSubject.getSinaUrl() + "\n");
					// eReport.append("*所属报表:"+reportCategory.getName()+"->"+accountingElement.getName()+"\n");
					eReport.append("*/\n");
					eReport.append("private Double " + accountingSubject.getCode().toLowerCase() + "_" + accountingSubject.getId() + " = null;\n");
					eReport.append("public void set" + CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, accountingSubject.getCode().toLowerCase()) + "_"
							+ accountingSubject.getId() + "(Double " + accountingSubject.getCode().toLowerCase() + "_" + accountingSubject.getId() + ") {\n");
					eReport.append(" this." + accountingSubject.getCode().toLowerCase() + "_" + accountingSubject.getId() + " = " + accountingSubject.getCode().toLowerCase() + "_"
							+ accountingSubject.getId() + ";\n");
					eReport.append("}\n");
					eReport.append("public Double get" + CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, accountingSubject.getCode().toLowerCase()) + "_"
							+ accountingSubject.getId() + "(){\n");
					eReport.append("return " + accountingSubject.getCode().toLowerCase() + "_" + accountingSubject.getId() + ";\n");
					eReport.append("}\n");

				}
				eReport.append("}\n");
				try {
					FileUtil.write(path + "/Ele" + accountingElement.getId() + ".java", eReport.toString());
					
				} catch (IOException e) {
					logger.error(">>FaceYe Throws Exception:", e);
				}
			}
			cReport.append("}\n");
			try {
				FileUtil.write(path + "/" + reportCategory.getCode() + ".java", cReport.toString());
			} catch (IOException e) {
				logger.error(">>FaceYe Throws Exception:", e);
			}
		}
		report.append("\n}");
		try {
			FileUtil.write(path + "/ReportData.java", report.toString());
		} catch (IOException e) {
			logger.error(">>FaceYe Throws Exception:", e);
		}
	}

}
