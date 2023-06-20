package com.example.healthmanagementoapp.form;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.healthmanagementoapp.dao.EntFormDao;
import com.example.healthmanagementoapp.dao.EntFormDao2;
import com.example.healthmanagementoapp.entity.Ent;

@Controller
public class HealthController {
	private EntFormDao entformdao = null;
	private EntFormDao2 entformdao2 = null;

	@Autowired
	public void FormController(EntFormDao entformdao, EntFormDao2 entformdao2) {
		this.entformdao = entformdao;
		this.entformdao2 = entformdao2;
		
	}
	@RequestMapping("/home")
	public String home(Model model, Input input) {
		model.addAttribute("title", "タイトルページ");

		return "home";

	}
	
	@RequestMapping("/form")
	public String form(Model model, Input input) {
		model.addAttribute("title", "診察者一覧ページ");
		List<Ent> list = entformdao.searchDb();
		model.addAttribute("dbList", list);

		return "form";

	}
	@RequestMapping("/batform")
	public String batform(Model model, Input input) {
		model.addAttribute("title", "異常あり一覧ページ");
		List<Ent> list2 = entformdao2.searchDb();
		model.addAttribute("dbList2", list2);

		return "batform";

	}
	@RequestMapping("/add")
	public String add(Model model, Input input) {
		model.addAttribute("title", "入力ページ");
//		List<Ent> list = entformdao.searchDb();
//		model.addAttribute("dbList", list);

		return "add";

	}
	@RequestMapping("/confirm")
	public String confirm(@Validated Input input, BindingResult result, Model model) {

		if (result.hasErrors()) {
			model.addAttribute("title", "入力ページ");
			return "add";
		}

		model.addAttribute("title", "確認ページ");
		return "confirm";
	}

	@RequestMapping("/complete")
	public String complete(Model model, Input input) {
		model.addAttribute("title", "完了ページ");
		Ent entform = new Ent();
		String health = "異常なし";
		
		entform.setType(health);
		
		entform.setName(input.getName());
		entform.setAge(input.getAge());
		entform.setSinntyou(input.getSinntyou());
		entform.setTaijuu(input.getTaijuu());
		entform.setKetuatuue(input.getKetuatuue());
		entform.setKetuatusita(input.getKetuatusita());
		entform.setMemo(input.getMemo());

		entformdao.insertDb(entform);

		return "complete";

	}
	@RequestMapping("/complete2")
	public String complete2(Model model, Input input) {
		model.addAttribute("title", "完了ページ");
		Ent entform = new Ent();
		String health = "異常あり";
		
		entform.setType(health);
		
		entform.setName(input.getName());
		entform.setAge(input.getAge());
		entform.setSinntyou(input.getSinntyou());
		entform.setTaijuu(input.getTaijuu());
		entform.setKetuatuue(input.getKetuatuue());
		entform.setKetuatusita(input.getKetuatusita());
		entform.setMemo(input.getMemo());

		entformdao.insertDb(entform);
		entformdao2.insertDb(entform);

		return "complete";

	}
	
	
	//削除(DELETE)
		@RequestMapping("/del/{id}")
		public String destory(@PathVariable Long id) {
			entformdao.deleteDb(id);
//			entformdao2.deleteDb(id);
			return "redirect:/form";
		}

		//削除(DELETE)
		@RequestMapping("/del2/{id}")
		public String destory2(@PathVariable Long id) {
			entformdao2.deleteDb(id);
			return "redirect:/batform";
		}
		
		@RequestMapping("/cha/{id}")
		public String change(@PathVariable Long id, Input input) {
			Ent entform = new Ent();
			
			String health = "異常あり";

			entform.setType(health);

			entform.setName(input.getName());
			entform.setAge(input.getAge());
			entform.setSinntyou(input.getSinntyou());
			entform.setTaijuu(input.getTaijuu());
			entform.setKetuatuue(input.getKetuatuue());
			entform.setKetuatusita(input.getKetuatusita());
			entform.setMemo(input.getMemo());
			entform.setType(input.getType());

			entformdao2.insertDb(entform);
			return "redirect:/form";
		}
//		//更新画面の表示(SELECT)
//		@RequestMapping("/edit/{id}")
//		public String editView(@PathVariable Long id, Model model) {
//
//			//DBからデータを1件取ってくる(リストの形)
//			List<Ent> list = entoformdao.selectOne(id);
//
//			//リストから、オブジェクトだけをピックアップ
//			EntForm entformdb = list.get(0);
//
//			//スタンバイしているViewに向かって、データを投げる
//			model.addAttribute("form", entformdb);
//			model.addAttribute("title", "編集ページ");
//			return "edit";
//		}
//
//		//更新処理(UPDATE)
//		@RequestMapping("/edit/{id}/exe")
//		public String editExe(@PathVariable  Long id , Model model, Input form) {
//			
//			//フォームの値をエンティティに入れ直し
//			
//			EntForm entform2 = new EntForm();
//			System.out.println(form.getName());//取得できているかの確認
//			System.out.println(form.getList());
//			entform2.setName(form.getName());
//			entform2.setList(form.getList());
//			entform2.setDate(form.getDate());
//			
//			//更新の実行
//			sampledao.updateDb(id, entform2);
//			//一覧画面へリダイレクト
//			return "redirect:/form";
//		}
//
//		@RequestMapping("/edit2/{id}")
//		public String editView2(@PathVariable Long id, Model model) {
//
//			//DBからデータを1件取ってくる(リストの形)
//			List<EntForm2> list = rosterdao.selectOne2(id);
//
//			//リストから、オブジェクトだけをピックアップ
//			EntForm2 entformdb = list.get(0);
//
//			//スタンバイしているViewに向かって、データを投げる
//			model.addAttribute("form2", entformdb);
//			model.addAttribute("title", "編集ページ");
//			return "edit2";
//		}
//
//		//更新処理(UPDATE)
//		@RequestMapping("/edit2/{id}/exe")
//		public String editExe(@PathVariable Long id,Model model, Input2 input2) {
//
//			
//			//フォームの値をエンティティに入れ直し
//			EntForm2 entform = new EntForm2();
//
//			entform.setName(input2.getName());
//			entform.setYakusyoku(input2.getYakusyoku());
//			entform.setBusyo(input2.getBusyo());
//			entform.setSyumi(input2.getSyumi());
//			entform.setComment(input2.getComment());
//			
//			//更新の実行
//			rosterdao.updateDb2(id, entform);
//
//			//一覧画面へリダイレクト
//			return "redirect:/form2";
//		}

}

